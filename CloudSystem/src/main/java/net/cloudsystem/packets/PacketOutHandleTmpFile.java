package net.cloudsystem.packets;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cloudsystem.abstractes.Packet;
import net.cloudsystem.managers.CloudManager;
import org.apache.commons.io.FileUtils;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 19:56:36
 * @Created on 27.08.2017, 19:56:36
 */
public class PacketOutHandleTmpFile extends Packet {

    private final String path;
    private final String notfile;
    private final String directory;
    private final int size;

    public PacketOutHandleTmpFile(String path, String notfile, String directory, int size) {
        this.path = path;
        this.notfile = notfile;
        this.directory = directory;
        this.size = size;
    }

    @Override
    public void execute() {
        File f = new File(path);
        File[] files = f.listFiles();

        for (int i = 0; i != files.length; i++) {
            File file1 = files[i];
            if (!file1.getName().equalsIgnoreCase(notfile)) {
                if (size == 1) {
                    try {
                        if (!file1.isDirectory()) {
                            FileUtils.copyFileToDirectory(file1, new File(directory));
                        } else {
                            FileUtils.copyDirectoryToDirectory(file1, new File(directory));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        for (int i1 = 1; i1 != size; i1++) {
                            if (!file1.isDirectory()) {
                                FileUtils.copyFileToDirectory(file1, new File(directory.replaceAll("%i%", String.valueOf(i1))));
                            } else {
                                FileUtils.copyDirectoryToDirectory(file1, new File(directory.replaceAll("%i%", String.valueOf(i1))));
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}
