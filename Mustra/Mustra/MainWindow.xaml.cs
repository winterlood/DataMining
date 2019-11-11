using Mustra.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Mustra.Model;
using System.Windows.Controls.Primitives;

namespace Mustra
{
    /// <summary>
    /// MainWindow.xaml에 대한 상호 작용 논리
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            this.DataContext = MainWindowViewModel.instance;
        }

        public void close(object sender, RoutedEventArgs r) 
        {
            this.Close();
        }

        private void Hyperlink_Click(object sender, RoutedEventArgs e)
        {
            string artN = ArtistName.Text as string;
            string songN = SongName.Text as string;
            string fanNum = FanNum.Text as string;
            string videoChk = MVChk.IsChecked.Value? "yes":"no";

            InstancePacket instancePacket = new InstancePacket(
                artN,songN,fanNum,videoChk);

            MainWindowViewModel mwvm = MainWindowViewModel.instance;


            mwvm.sendNewInstance(instancePacket);

            PredictUserControlViewModel pucv = PredictUserControlViewModel.instance;

            pucv.AFR = fanNum;
            pucv.SMR = videoChk;

            PredictButton.IsChecked = true;
            mwvm.loadPredictPage(artN);
        }
    }
}
