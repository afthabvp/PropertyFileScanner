package com.example.filescanner.imp;

import java.io.File;
import java.util.HashMap;
import java.util.TimerTask;

public abstract class FileNotifier extends TimerTask {
  private String path;
  private File filesArray [];
  private HashMap<File, Long> dir = new HashMap<File, Long>();

  
  public FileNotifier(String path) {
    this.path = path;
    filesArray = new File(path).listFiles();

    for(int i = 0; i < filesArray.length; i++) {
       dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
    }
  }

  public final void run() {
   
    filesArray = new File(path).listFiles();

    // scan the files and check for modification/addition
    for(int i = 0; i < filesArray.length; i++) {
      Long current = (Long)dir.get(filesArray[i]);
      if (current.longValue() != filesArray[i].lastModified()){
        dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
        
        onChange(filesArray[i], "modify");
      }
    }
  }

  protected abstract void onChange( File file, String action );
}