<template>
    <v-app id="inspire">
            <v-app-bar
              app
              shrink-on-scroll
            >
              <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

              <v-toolbar-title>
                <v-card-actions>
                  <v-row>
                    <h4>Posts</h4>
                     <v-spacer></v-spacer>
                     <v-btn
                         color="deep-purple lighten-2"
                         text

                     >
                        <v-icon dark>
                          mdi-pencil
                        </v-icon>
                     </v-btn>
                     <v-btn
                       color="deep-purple lighten-2"
                       text
                         mr-0
                     >
                       <v-icon dark >
                        mdi-trash-can-outline
                       </v-icon>
                     </v-btn>
                  </v-row>
                </v-card-actions>
              </v-toolbar-title>

              <v-spacer></v-spacer>


              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-app-bar>


            <v-navigation-drawer
                  v-model="drawer"
                  fixed
                  temporary
                >
                <!--  -->
            </v-navigation-drawer>

     <v-main>
              <v-container>
                    <v-card>

                       <v-img
                         height="300"
                         src="https://travel-or-die.ru/wp-content/uploads/2016/11/rejkyavik-iz-moskvy-avia-e1477991523290.jpg"
                       ></v-img>

                       <v-row>
                         <v-card-title><strong>{{ post.header }}</strong></v-card-title>
                         <v-spacer></v-spacer>
                         <v-btn icon>
                             <v-icon>mdi-heart</v-icon>
                         </v-btn>

                          <v-btn icon>
                             <v-icon>mdi-bookmark</v-icon>
                          </v-btn>

                          <v-btn icon>
                              <v-icon>mdi-share-variant</v-icon>
                          </v-btn>
                       </v-row>

                       <v-card-text>
                           <!---<button @click="setEditorContent">Set Editor Contents</button>--->

                           <div hidden="true">
                                <vue-editor disabled="true" v-model="content" editorToolbar="[]" id="postContent"/>
                           </div>

                           <div id="postcontent">

                           </div>
                       </v-card-text>

                    </v-card>
              </v-container>
     </v-main>
    </v-app>
</template>

<script>
    import { mapGetters } from 'vuex';
    import { mapActions } from 'vuex'
    import { VueEditor, Quill } from 'vue2-editor'

    export default {
       data: () => (
            { drawer: null ,
             content: null }
       ),
       computed: mapGetters(['post', 'blog']),
       components: {
             VueEditor, Quill
       },
       methods: {
          ...mapActions(['addPostAction']),
          setEditorContent: function() {
             var editor = document.getElementById('postContent');
             var quill = new Quill(editor);
             var page = this.post.content;
             quill.setContents(JSON.parse(page));
             var mycontent = document.getElementById('postcontent');
             mycontent.innerHTML = quill.root.innerHTML;
             //quill.setContents(null);
          }
       },
       mounted(){
           this.setEditorContent()
       }
    }
</script>

<style>

</style>