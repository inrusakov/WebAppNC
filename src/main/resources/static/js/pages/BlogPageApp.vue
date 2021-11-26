<template>
    <v-app id="inspire">
        <v-app-bar
          app
          shrink-on-scroll
        >
          <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>


        <v-toolbar-title>
            Posts
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
           <v-row class="d-flex justify-bottom mt-6">
               <v-btn
                    color="deep-purple lighten-2"
                    text
                    class="d-flex justify-end mr-6"
                    v-bind:href="'/blogpage/' + blog.blogID + '/addpost'"
               >
                   <v-icon dark >
                        mdi-plus
                    </v-icon>
                    Add new post
               </v-btn>
            </v-row>
            <v-row>
              <v-col
                v-for="post in posts"
                :key="post.postId"
                cols="4"
              >
                <v-card height="300">
                     <v-img
                          height="100"
                          src="https://travel-or-die.ru/wp-content/uploads/2016/11/rejkyavik-iz-moskvy-avia-e1477991523290.jpg"
                     ></v-img>

                     <v-card-title><strong>{{ post.header }}</strong></v-card-title>

                     <v-row
                           align="center"
                           class="mx-5"
                     >
                     <v-rating
                         :value=post.rating
                         color="amber"
                         dense
                         half-increments
                         readonly
                         size="14"
                      ></v-rating>

                     <div class="grey--text ms-4">
                         {{ post.rating }}
                     </div>
                     </v-row>
                         <v-card-text>
                           <v-chip-group
                             v-model="selection"
                             active-class="deep-purple accent-4 white--text"
                             column
                           >
                           <v-row  align="center"
                               class="mx-8" >
                             <v-chip>Photos</v-chip>

                             <v-chip>Beautiful places</v-chip>

                             <v-chip>Nature</v-chip>

                             <v-chip>Adventure</v-chip>
                           </v-row>
                           </v-chip-group>
                         </v-card-text>

                         <v-card-actions>
                            <v-btn
                               color="deep-purple lighten-2"
                               text
                               v-bind:href="'/blogpage/' + blog.blogID + '/post/'+ post.postId"
                            >
                               Explore
                            </v-btn>
                         </v-card-actions>

                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-main>
      </v-app>
</template>

<script>
    import { mapGetters } from 'vuex';

     export default {
        data: () => ({ drawer: null }),
        computed: mapGetters(['posts', 'blog'])
     }
</script>

<style>

</style>