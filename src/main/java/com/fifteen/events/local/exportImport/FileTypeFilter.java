package com.fifteen.events.local.exportImport;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import lombok.AllArgsConstructor;

/**
 * A class to limit the file types that is shown on the JFileChooser. This
 * class implementation is taken from a youtube tutorial.
 * 
 * @see https://www.youtube.com/watch?v=lFkYt2jKrYc
 * 
 * @author Triet Huynh
 */
@AllArgsConstructor
public class FileTypeFilter extends FileFilter {
  private final String EXTENSION;
  private final String DESCRIPTION;

  /**
   * get the file name along with its extension
   * 
   * @author Triet Huynh
   */
  @Override
  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    }
    return file.getName().endsWith(EXTENSION);
  }

  /**
   * get the description of the file type
   * 
   * @author Triet Huynh
   */
  @Override
  public String getDescription() {
    return DESCRIPTION + String.format(" (*%s", EXTENSION);
  }

}
