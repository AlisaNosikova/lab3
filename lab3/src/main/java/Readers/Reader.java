/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Readers;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Reader {
    public ArrayList<Reactor> Read(File file);
    public String getFileType();
}
