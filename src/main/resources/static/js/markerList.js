let popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
let counter = 0;
// ELEMENT SELECTORS
let input = document.querySelector('#userinput');
let ul = document.querySelector('ul');
let body = document.querySelector('body');

// HELPER FUNCTIONS
function deleteListItem(event) {
    deleteMarker(event.target.parentNode.classList[3]);
    counter--;
    event.srcElement.parentNode.remove();

    refactor();
}

function refactor(){
    let obj_list = document.querySelector('#list');
    let objects = [];

    obj_list.childNodes.forEach((node, i) =>
    {
        try {
            let number = i-1;
            let content = node.innerHTML.split("<h")[0].split("</")[0].split(">")[1];

            let num = document.createElement('h4');
            num.innerHTML = '.'+number;
            num.classList.add('num');

            let itemDiv = document.createElement('div');
            itemDiv.classList.add('itemdiv', 'width', 'margin', number);

            let deleteButton = document.createElement('button');
            deleteButton.innerHTML = 'x';
            deleteButton.classList.add('btn', 'btn-outline-secondary', 'bg-light', 'deletebutton');

            let li = document.createElement('li');
            li.addEventListener('dblclick', editItem);
            li.textContent = content;

            itemDiv.append(li, num, deleteButton);

            deleteButton.addEventListener('click', event => {
                    li.removeEventListener('dblclick', editItem);

                    deleteListItem(event);

                    li = null;
                    deleteButton = null;
                    itemDiv = null;
                },
                {once: true}
            );
            ul.append(itemDiv);
            objects.push(node);
        } catch (er){}
    })

    objects.forEach(node => node.remove());
}

function createDivAndButton(li) {
    let num = document.createElement('h4');
    num.innerHTML = '.'+counter;
    num.classList.add('num');

    let itemDiv = document.createElement('div');
    itemDiv.classList.add('itemdiv', 'width', 'margin', counter);

    let deleteButton = document.createElement('button');
    deleteButton.innerHTML = 'x';
    deleteButton.classList.add('btn', 'btn-outline-secondary', 'bg-light', 'deletebutton');

    counter++;
    return {
        num,
        itemDiv,
        deleteButton
    };
}

function saveItem(event) {
    if (event.target.value.length > 0 && (event.keyCode === 13 || event.type === 'click')) {
        let li = document.createElement('li');
        li.addEventListener('dblclick', editItem);
        li.textContent = event.target.value;
        changeTooltip(event.target.parentNode.classList[3],event.target.value);
        event.target.parentNode.prepend(li);
        event.target.remove();
    } else if (event.target.value.length === 0 && (event.keyCode === 13 || event.type === 'click')) {
        let li = document.createElement('li');
        li.addEventListener('dblclick', editItem);
        li.textContent = initialValue;
        event.target.parentNode.prepend(li);
        event.target.remove();
    }
}

// TO SAVE VALUE OF ITEM BEFORE IT IS EDITED
let initialValue;

function editItem(event) {
    let item = event.target.innerHTML;
    let itemInput = document.createElement('input');
    itemInput.type = 'text';
    itemInput.value = item;
    itemInput.classList.add('edit');
    initialValue = item;
    itemInput.addEventListener('keypress', saveItem);
    itemInput.addEventListener('click', saveItem);
    event.target.parentNode.prepend(itemInput);
    event.target.remove();
    itemInput.select();
}

function createListItem(marker) {
    let li = document.createElement('li');
    li.addEventListener('dblclick', editItem);
    li.textContent = marker;

    let {num, itemDiv, deleteButton} = createDivAndButton();

    itemDiv.append(li, num, deleteButton);
    ul.append(itemDiv);

    deleteButton.addEventListener('click', event => {
            li.removeEventListener('dblclick', editItem);

            deleteListItem(event);

            li = null;
            deleteButton = null;
            itemDiv = null;
        },
        {once: true}
    );
}

function clearList(event) {
    ul.innerHTML = '';
    counter = 0;
    event.target.remove();
}
