import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CreatePlaylistComponent } from './create-playlist/create-playlist.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';

import { PlaylistsongComponent } from './playlistsong/playlistsong.component';
import { RegisterComponent } from './register/register.component';
import { ViewPlaylistComponent } from './view-playlist/view-playlist.component';
import { ViewSongComponent } from './view-song/view-song.component';

const routes: Routes = [{
  path:"",
  component:ViewSongComponent
},{path:"home",
component:ViewSongComponent},
  {path:"login",
component:LoginComponent,
},

  {
    path:"register",
    component:RegisterComponent,

  },{
    path:"createPlaylist",
    component:CreatePlaylistComponent,

  },{
    path:"allTracks",
    component:ViewSongComponent
  },
  {
    path:"viewPlaylist",
    component:ViewPlaylistComponent
  },{
    path:"Playlistsong/:id",
    component:PlaylistsongComponent
  },{
    path:"viewPlaylist/:id",
    component:ViewPlaylistComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
