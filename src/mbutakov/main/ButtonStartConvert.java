package mbutakov.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

/**
 * Created by matveibutakov on 19.06.2020.
 */
public class ButtonStartConvert implements ActionListener {

    public void actionPerformed(ActionEvent e) {
    	Main.rename();
    	System.exit(0);
    }
}
