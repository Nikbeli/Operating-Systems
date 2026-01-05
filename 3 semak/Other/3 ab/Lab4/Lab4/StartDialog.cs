namespace Lab4
{
    public partial class StartDialog : Form
    {
        public StartDialog()
        {
            InitializeComponent();
        }

        private void start_Click(object sender, EventArgs e)
        {
            int RAM = (int)memory.getValue();
            if (RAM > 560)
            {
                RAM = 560;
            }
            Disñ disk = new Disñ(RAM);
        }
    }
}