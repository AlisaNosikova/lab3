/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Chain;

import Readers.Reactor;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Handler {
    public void setNext(Handler next);
    public ArrayList<Reactor> handle(File file);
}
