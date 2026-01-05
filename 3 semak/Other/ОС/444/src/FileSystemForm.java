import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FileSystemForm {
    private FileManager fileManager;
    private RAM ram = null;
    private CellMeshMemoryPanel cellMeshMemoryPanel;
    private JFrame frame;
    private DefaultMutableTreeNode treeFile;
    private JTree treeFileSystem;


    public static void main(String[] args) {
        EventQueue.invokeLater( new Runnable() {
            public void run() {
                try {
                    FileSystemForm window = new FileSystemForm();
                    window.frame.setVisible( true );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    /**
     * Create the application.
     */
    public FileSystemForm() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds( 0, 0, 1200, 800 );
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.getContentPane().setLayout( null );

        JLabel lblName = new JLabel( "Имя:" );
        lblName.setBounds( 1000, 250, 100, 30 );
        frame.getContentPane().add( lblName );

        JTextField textFieldName = new JTextField( "new" );
        textFieldName.setBounds( 1000, 290, 150, 30 );
        textFieldName.setEnabled( false );
        frame.getContentPane().add( textFieldName );

        JLabel lblSizeFile = new JLabel( "Размер:" );
        lblSizeFile.setBounds( 1000, 330, 100, 30 );
        frame.getContentPane().add( lblSizeFile );

        JTextField textFieldFile = new JTextField( "1" );
        textFieldFile.setEnabled( false );
        textFieldFile.setBounds( 1000, 370, 150, 30 );
        frame.getContentPane().add( textFieldFile );

        JButton btnCreateFile = new JButton( "Создать файл" );
        btnCreateFile.setBounds( 1000, 50, 150, 30 );
        btnCreateFile.setEnabled( false );
        btnCreateFile.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.createFile( textFieldName.getText(), 0, Integer.parseInt( textFieldFile.getText() ) );
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnCreateFile );

        JButton btnCreateFolder = new JButton( "Создать папку" );
        btnCreateFolder.setBounds( 1000, 90, 150, 30 );
        btnCreateFolder.setEnabled( false );
        btnCreateFolder.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.createFile( textFieldName.getText(), 1, 1 );
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnCreateFolder );

        JButton btnCopy = new JButton( "Скопировать" );
        btnCopy.setBounds( 1000, 130, 150, 30 );
        btnCopy.setEnabled( false );
        btnCopy.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.copy();
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnCopy );

        JButton btnPaste = new JButton( "Вставить" );
        btnPaste.setBounds( 1000, 170, 150, 30 );
        btnPaste.setEnabled( false );
        btnPaste.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.paste();
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnPaste );

        JButton btnDelete = new JButton( "Удалить" );
        btnDelete.setBounds( 1000, 210, 150, 30 );
        btnDelete.setEnabled( false );
        btnDelete.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.delete();
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnDelete );

        JButton btnMoveSelectFile = new JButton( "Выбрать для перемещения" );
        btnMoveSelectFile.setBounds( 1000, 420, 180, 30 );
        btnMoveSelectFile.setEnabled( false );
        btnMoveSelectFile.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.move();
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnMoveSelectFile );

        JButton btnMoveComplete = new JButton( "Переместить" );
        btnMoveComplete.setBounds( 1000, 460, 150, 30 );
        btnMoveComplete.setEnabled( false );
        btnMoveComplete.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.moveComplete();
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
                frame.repaint();
            }
        } );
        frame.getContentPane().add( btnMoveComplete );

        JLabel labelSizeDisc = new JLabel( "Размер диска" );
        labelSizeDisc.setBounds( 1000, 520, 100, 30 );
        frame.getContentPane().add( labelSizeDisc );

        JTextField textFieldSizeDisc = new JTextField();
        textFieldSizeDisc.setBounds( 1000, 540, 100, 30 );
        frame.getContentPane().add( textFieldSizeDisc );

        JLabel labelSizeSector = new JLabel( "Размер сектора" );
        labelSizeSector.setBounds( 1000, 570, 100, 30 );
        frame.getContentPane().add( labelSizeSector );

        JTextField textFieldSizeSector = new JTextField();
        textFieldSizeSector.setBounds( 1000, 590, 100, 30 );
        frame.getContentPane().add( textFieldSizeSector );

        JLabel lblZeroCellMeshMemory = new JLabel( "" );
        lblZeroCellMeshMemory.setBounds( 300, 480, 300, 40 );
        frame.getContentPane().add( lblZeroCellMeshMemory );

        JLabel lblFillCellMeshMemory = new JLabel( "" );
        lblFillCellMeshMemory.setBounds( 300, 540, 300, 40 );
        frame.getContentPane().add( lblFillCellMeshMemory );

        JLabel lblCurrentCellMeshMemory = new JLabel( "" );
        lblCurrentCellMeshMemory.setBounds( 300, 590, 300, 40 );
        frame.getContentPane().add( lblCurrentCellMeshMemory );

        JButton btnSpecifyMemorySize = new JButton( "Задать размер памяти" );
        btnSpecifyMemorySize.setBounds( 1000, 630, 180, 30 );
        btnSpecifyMemorySize.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldSizeDisc.setEditable( false );
                ;
                textFieldSizeSector.setEditable( false );
                btnCreateFile.setEnabled( true );
                btnDelete.setEnabled( true );
                btnMoveSelectFile.setEnabled( true );
                btnCopy.setEnabled( true );
                btnCreateFolder.setEnabled( true );
                btnPaste.setEnabled( true );
                btnMoveComplete.setEnabled( true );
                btnSpecifyMemorySize.setEnabled( false );
                lblName.setEnabled( true );
                textFieldName.setEnabled( true );
                textFieldFile.setEnabled( true );
                lblZeroCellMeshMemory.setText( "Пустые ячейки памяти диска" );
                lblFillCellMeshMemory.setText( "Заполненные ячейки памяти диска" );
                lblCurrentCellMeshMemory.setText( "Выбранные текущие ячейки памяти диска" );
                ram = new RAM( Integer.parseInt( textFieldSizeDisc.getText() ), Integer.parseInt( textFieldSizeSector.getText() ) );
                cellMeshMemoryPanel = new CellMeshMemoryPanel( ram );
                cellMeshMemoryPanel.setBounds( 210, 10, 900, 650 );
                frame.getContentPane().add( cellMeshMemoryPanel );
                fileManager = new FileManager( ram );
                ram.chooseFile( fileManager.getRootFile() );
                startChangeFileSystemTree( fileManager.getRootFile().getChild() );
            }
        } );
        frame.repaint();
        frame.getContentPane().add( btnSpecifyMemorySize );
    }

    private void startChangeFileSystemTree(LinkedList<File> child) {
        treeFile = new DefaultMutableTreeNode( fileManager.getRootFile() );
        changeFileTree( treeFile, child );
        if (!Objects.isNull( treeFileSystem )) {
            frame.getContentPane().remove( treeFileSystem );
        }
        treeFileSystem = new JTree( treeFile );
        treeFileSystem.setEnabled( true );
        treeFileSystem.setShowsRootHandles( true );
        treeFileSystem.setBounds( 0, 0, 210, 400 );
        treeFileSystem.addTreeSelectionListener( new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                fileManager.setSelectedFile( (DefaultMutableTreeNode) Objects.requireNonNull( treeFileSystem.getSelectionPath() ).getLastPathComponent() );
                ram.chooseFile( fileManager.getSelected() );
                frame.repaint();
            }
        } );
        frame.getContentPane().setLayout( null );
        frame.getContentPane().add( treeFileSystem );
        treeFileSystem.updateUI();
        treeFileSystem.setScrollsOnExpand( true );
    }

    private void changeFileTree(DefaultMutableTreeNode treeFile, LinkedList<File> child) {
        for (File file : child) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode( file );
            treeFile.add( newNode );
            if (file.isFolder() == 1) { //если это папка то делаем новый узел для неё
                changeFileTree( newNode, file.getChild() );
            }
        }
    }
}