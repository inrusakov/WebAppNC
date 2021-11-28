<template>
     <v-app id="inspire">

        <v-system-bar app>
              <v-spacer></v-spacer>

              <v-icon>mdi-square</v-icon>

              <v-icon>mdi-circle</v-icon>

              <v-icon>mdi-triangle</v-icon>

        </v-system-bar>

        <v-app-bar app>
              <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

              <v-toolbar-title>Blogs</v-toolbar-title>
        </v-app-bar>

         <v-navigation-drawer
              v-model="drawer"
              fixed
              temporary
            >
            <!--  -->
         </v-navigation-drawer>

            <v-main class="grey lighten-2">
              <v-container>
                <v-row>
                  <template v-for="blog in blogs">


                    <v-col
                      cols="3"
                      md="5"
                    >
                        <v-card>
                            <v-img
                                  height="100"
                                  src="https://fijivoyage.com/wp-content/uploads/2020/05/Norway-oslo-oslo-norway.jpg"
                            ></v-img>

                            <v-card-title><strong>{{ blog.user.firstName + ' ' + blog.user.lastName }}</strong></v-card-title>


                            <v-card-text>
                             <v-chip-group
                                      v-model="selection"
                                      active-class="deep-purple accent-4 white--text"
                                      column
                                    >
                                    <v-row  align="center"
                                            class="mx-5">
                                      <v-chip>???</v-chip>

                                      <v-chip>???</v-chip>

                                      <v-chip>???</v-chip>

                                      <v-chip>???</v-chip>
                                    </v-row>
                             </v-chip-group>
                            </v-card-text>

                            <v-card-actions>
                                    <v-btn
                                      color="deep-purple lighten-2"
                                      text
                                      v-bind:href="'/blogpage/'+ blog.blogID"
                                    >
                                      Show posts
                                    </v-btn>
                            </v-card-actions>

                        </v-card>

                    </v-col>
                  </template>
                </v-row>
              </v-container>
            </v-main>

     </v-app>
</template>

<script>
     import { mapGetters } from 'vuex';

     export default {
        data: () => ({ drawer: null }),
        computed: mapGetters(['blogs'])
      }

</script>

<style>

</style>