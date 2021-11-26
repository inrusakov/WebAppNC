<template>
    <v-app id="inspire">
        <v-app-bar
          app
          color="white"
          flat
        >
          <v-avatar
            :color="$vuetify.breakpoint.smAndDown ? 'grey darken-1' : 'transparent'"
            size="32"
          ></v-avatar>

          <v-tabs
            centered
            class="ml-n9"
            color="grey darken-1"
          >
            <v-tab
              v-for="link in links"
              :key="link"
            >
              {{ link }}
            </v-tab>
          </v-tabs>

          <v-avatar
            class="hidden-sm-and-down"
            color="grey darken-1 shrink"
            size="32"
          ></v-avatar>
        </v-app-bar>

        <v-main class="grey lighten-3">
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="2"
                  >
                    <v-sheet
                      rounded="lg"
                      min-height="268"
                    >
                      <!--  -->
                    </v-sheet>
                  </v-col>

                  <v-col
                    cols="12"
                    sm="8"
                  >
                    <v-sheet
                      min-height="70vh"
                      rounded="lg"
                    >
                      <input type="text" name="header" id="" cols="90" rows="7" v-model="header"/>
                      <vue-editor v-model="content" /> <!--- @text-change="onTextChange" --->

                    </v-sheet>
                  </v-col>

                  <v-col
                    cols="12"
                    sm="2"
                  >
                    <v-sheet
                      rounded="lg"
                      min-height="268"
                    >
                     <v-btn icon name="addPost" @click="save" class="py-6 mx-8">
                           <v-icon>mdi-bookmark</v-icon> Save
                     </v-btn>
                    </v-sheet>
                  </v-col>
                </v-row>
              </v-container>
            </v-main>

    </v-app>
</template>

<script>
    import { mapGetters } from 'vuex';
    import { mapActions } from 'vuex'
    import { VueEditor, Quill } from 'vue2-editor'

    export default {
        data: () => ({
          links: [
            'Dashboard',
            'Messages',
            'Profile',
            'Updates',
          ],
          content: '',
          header: ''
        }),
        computed: mapGetters(['post', 'blog']),
        components: {
            VueEditor, Quill
        },
        methods: {
            ...mapActions(['addPostAction']),
            save(){
                console.log("Working!");
                console.log(this.content);
                console.log(this.header);
                this.post.header = this.header;
                this.post.content = this.content;
                console.log(this.post);
                this.addPostAction(this.post);
            },
            /*onTextChange(quill){
                //var delta = quill.getContents();
                console.log(quill.root);
                //this.content = JSON.stringify(delta);
                console.log(this.content);
                console.log(this.header);
            }*/
        }
    }
</script>

<style>

</style>