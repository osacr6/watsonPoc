/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.awt.Color;

/**
 *
 * @author d.murillo.porras
 */
public abstract class ChatConf {
    private Color colorBot = new java.awt.Color(240, 255, 255);
    private Color ColorUsuario = new java.awt.Color(240, 240, 240);

    public Color getColorBot() {
        return colorBot;
    }

    public Color getColorUsuario() {
        return ColorUsuario;
    }
}
