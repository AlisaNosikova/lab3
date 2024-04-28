/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chain;

import Readers.Reactor;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class BaseHandler implements Handler{
    Handler nextHandler;
    private ArrayList<Reactor> list;

    @Override
    public void setNext(Handler next) {
        nextHandler = next;
    }

    @Override
    public abstract ArrayList<Reactor> handle(File file);

}
