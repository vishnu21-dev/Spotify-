export type User=
{
  emailId: String,
  name: String,
  address: String,
  password: String,
  userPlayList?: [
    {
      name: String,
      tracks: [
        {
          id: 0,
          name: String,
          artist: String
        }
      ]
    }
  ]
}

