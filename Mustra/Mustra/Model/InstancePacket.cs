using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mustra.Model
{
    class InstancePacket
    {
        private string artistName;
        private string songName;
        private string fanNumber;
        private string videoChk;

        public InstancePacket(string artistName, string songName, string fanNumber, string videoChk)
        {
            ArtistName = artistName;
            SongName = songName;
            FanNumber = fanNumber;
            VideoChk = videoChk;
        }

        public string ArtistName { get => artistName; set => artistName = value; }
        public string SongName { get => songName; set => songName = value; }
        public string FanNumber { get => fanNumber; set => fanNumber = value; }
        public string VideoChk { get => videoChk; set => videoChk = value; }
    }
}
