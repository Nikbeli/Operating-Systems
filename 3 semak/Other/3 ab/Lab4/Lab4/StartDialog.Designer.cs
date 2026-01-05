namespace Lab4
{
    partial class StartDialog
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label = new System.Windows.Forms.Label();
            this.memory = new System.Windows.Forms.TextBox();
            this.start = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label
            // 
            this.label.AutoSize = true;
            this.label.Location = new System.Drawing.Point(18, 18);
            this.label.Name = "label";
            this.label.Size = new System.Drawing.Size(127, 15);
            this.label.TabIndex = 0;
            this.label.Text = "Задайте размер диска";
            // 
            // memory
            // 
            this.memory.Location = new System.Drawing.Point(29, 45);
            this.memory.Name = "memory";
            this.memory.Size = new System.Drawing.Size(100, 23);
            this.memory.TabIndex = 1;
            this.memory.Text = "50";
            // 
            // start
            // 
            this.start.Location = new System.Drawing.Point(40, 74);
            this.start.Name = "start";
            this.start.Size = new System.Drawing.Size(75, 23);
            this.start.TabIndex = 2;
            this.start.Text = "Запуск";
            this.start.UseVisualStyleBackColor = true;
            this.start.Click += new System.EventHandler(this.start_Click);
            // 
            // StartDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(166, 125);
            this.Controls.Add(this.start);
            this.Controls.Add(this.memory);
            this.Controls.Add(this.label);
            this.Name = "StartDialog";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label;
        private TextBox memory;
        private Button start;
    }
}