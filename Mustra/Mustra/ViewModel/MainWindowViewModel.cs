
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Input;
using Mustra.InterFace;

namespace Mustra.ViewModel
{
    class MainWindowViewModel : INotifyPropertyChanged
    {
        private object _contentView;
        private PredictUserControlViewModel _predictUserControlViewModel;
        public object ContentView
        {
            get { return this._contentView; }
            set
            {
                this._contentView = value;
                this.OnPropertyChanged("ContentView");
            }
        }

        public ICommand LoadPredictPage { get; set; }

        public MainWindowViewModel()
        {
            this.ContentView = null;
            this.LoadPredictPage = new Command(loadPredictPage,CE);
            _predictUserControlViewModel = new PredictUserControlViewModel();


        }

        #region Page Change Operations
        private void loadPredictPage(object e) =>this._contentView = this._predictUserControlViewModel;
        private Boolean CE(object e)=>true;
        #endregion


        protected void OnPropertyChanged(string name)
        {
         PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
        }
        public event PropertyChangedEventHandler PropertyChanged;
    }
}
