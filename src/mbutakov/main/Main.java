package mbutakov.main;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

public class Main {

	public static final String VERSION = "0.0.2";
	public static String pathToDirectory;
	public static String pathToDirectory2;
	public static File CatalogPhoto;
	public static File sortedCatalogPhoto;
	
    public static void main(String[] args) {
    	int countImg = 0;
        URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        pathToDirectory = System.getProperty("user.dir")+"/photo/";
        pathToDirectory2 = System.getProperty("user.dir")+"/sortedPhoto/";
        CatalogPhoto = new File(pathToDirectory); 
        System.out.println(pathToDirectory);
        sortedCatalogPhoto = new File(pathToDirectory2); 
        
        if(!sortedCatalogPhoto.isDirectory()) {
        	sortedCatalogPhoto.mkdir();
        }
        
        if(CatalogPhoto.isDirectory())
        {
        	
        }else{
        	CatalogPhoto.mkdir();
        }
        
        for(File img : CatalogPhoto.listFiles()){
        	countImg++;
        }
        JFrame guiPane = new JFrame("Renamer " + VERSION);
        JButton buttonReame = new JButton("Переименовать");
        JLabel textPath;
        textPath = new JLabel("Обнаружено: " + countImg + " фотографий");
        textPath.setBounds(40,40,500,20);
        buttonReame.setBounds(40,90,155,20);
        ActionListener actionListener = new ButtonStartConvert();
        buttonReame.addActionListener(actionListener);
        guiPane.add(textPath);
        guiPane.add(buttonReame);
        guiPane.setSize(500,200);
        guiPane.setLayout(null);
        guiPane.setVisible(true);
    }
    
    public static void rename() {
    	long millisec = 0;
        File[] files = Main.CatalogPhoto.listFiles();
        Path destDir = Main.sortedCatalogPhoto.toPath();
        
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return Long.compare(f1.lastModified(), f2.lastModified());
            }
        });
    
    
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String nameFile = file.getName();
            try {
            	long datetime = file.lastModified();
        		Date d = new Date(datetime);
        		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        		String dateString = sdf.format(d);
        		System.out.println(dateString);
        		if(nameFile.endsWith("png")) {
        			Files.copy(file.toPath(), destDir.resolve(dateString  + "("+ i + ")" + ".png"));
    				
        		}else if (nameFile.endsWith("jpg")){
        			Files.copy(file.toPath(), destDir.resolve(dateString  + "("+ i + ")" + ".jpg"));
        		}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            
        }
        System.exit(0);
    }
    
}
