package com.fifteen.events.local.exportImport;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import lombok.AllArgsConstructor;

/**
 * https://www.youtube.com/watch?v=lFkYt2jKrYc
 */
@AllArgsConstructor
public class FileTypeFilter extends FileFilter {
  private final String EXTENSION;
  private final String DESCRIPTION;

  @Override
  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    }
    return file.getName().endsWith(EXTENSION);
  }

  @Override
  public String getDescription() {
    return DESCRIPTION + String.format(" (*%s", EXTENSION);
  }

}
