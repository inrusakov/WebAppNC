let counter = 1;
// ELEMENT SELECTORS
let input = document.querySelector('#userinput');
let ul = document.querySelector('ul');
let body = document.querySelector('body');

// HELPER FUNCTIONS
function deleteListItem(event) {
    let deleted = event.target.parentNode.classList[3] - 1;
    if (deleted<0)
        deleted = 0;
    deleteMarker(deleted);
    counter--;
    event.target.parentNode.remove();
    refactor();
}

function drag(){
    const swappable = new Draggable.Swappable(document.querySelectorAll('ul'), {
        draggable: 'li'
    });

    swappable.on('swappable:start', () => console.log('swappable:start'));
    swappable.on('swappable:swapped', () => console.log('swappable:swapped'));
    swappable.on('swappable:stop', () => console.log('swappable:stopped'));
}

function refactor(){
    let obj_list = document.querySelector('#list');
    let objects = [];

    obj_list.childNodes.forEach((node, i) =>
    {
        try {
            let number = i;
            let content = node.innerHTML.split(">")[1].split("<")[0];

            let itemDiv = document.createElement('li');
            itemDiv.classList.add('itemdiv', 'width', 'margin', number);

            let deleteButton = document.createElement('button');
            deleteButton.innerHTML = 'x';
            deleteButton.classList.add('btn', 'btn-outline-secondary', 'bg-light', 'deletebutton');

            let text = document.createElement('div');
            text.classList.add("marker-text");
            text.addEventListener('dblclick', editItem);
            text.textContent = content;

            itemDiv.append(text, deleteButton);

            deleteButton.addEventListener('click', event => {
                    text.removeEventListener('dblclick', editItem);

                    deleteListItem(event);

                    text = null;
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
    let itemDiv = document.createElement('li');
    itemDiv.classList.add('itemdiv', 'width', 'margin', counter);

    let deleteButton = document.createElement('button');
    deleteButton.innerHTML = 'x';
    deleteButton.classList.add('btn', 'btn-outline-secondary', 'bg-light', 'deletebutton');

    counter++;
    return {
        itemDiv,
        deleteButton
    };
}

function saveItem(event) {
    if (event.target.value.length > 0 && (event.keyCode === 13 || event.type === 'click')) {
        let text = document.createElement('div');
        text.classList.add("marker-text");
        text.addEventListener('dblclick', editItem);
        text.textContent = event.target.value;
        let num = event.target.parentNode.classList[3]-1;
        changeTooltip(num,event.target.value);
        event.target.parentNode.prepend(text);
        event.target.remove();
    } else if (event.target.value.length === 0 && (event.keyCode === 13 || event.type === 'click')) {
        let text = document.createElement('div');
        text.classList.add("marker-text");
        text.addEventListener('dblclick', editItem);
        text.textContent = initialValue;
        event.target.parentNode.prepend(text);
        event.target.remove();
    }
}

// TO SAVE VALUE OF ITEM BEFORE IT IS EDITED
let initialValue;

function editItem(event) {
    let item = event.target.innerHTML;
    let itemInput = document.createElement('input');
    //itemInput.style.width = (item.length+1)*8+'px';
    itemInput.type = 'text';
    itemInput.id = 'text';
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
    let text = document.createElement('div');
    text.classList.add("marker-text");
    text.addEventListener('dblclick', editItem);
    text.textContent = marker;

    let {itemDiv, deleteButton} = createDivAndButton();

    itemDiv.append(text, deleteButton);
    ul.append(itemDiv);

    deleteButton.addEventListener('click', event => {
            text.removeEventListener('dblclick', editItem);

            deleteListItem(event);

            text = null;
            deleteButton = null;
            itemDiv = null;
        },
        {once: true}
    );

}

function clearList(event) {
    ul.innerHTML = '';
    counter = 1;
}
