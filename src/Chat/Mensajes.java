/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;
import Ventanas.*;
import com.ibm.watson.assistant.v2.model.MessageResponse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author d.murillo.porras
 */
public class Mensajes extends ChatConf {
    private int index = 0;
    private JPanel[] chatList = new JPanel[100];
    private JPanel mainPanel = new JPanel(new GridLayout(0, 1));

    public Mensajes() {
    }
    
    private void renderMainPanel(JPanel msjpanel) {
        mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.setBackground(Color.WHITE);
        
        if(msjpanel != null){
            msjpanel.setName("panel-"+index);
            chatList[index] = msjpanel;
            index++;
        }
        
        for (int i = 0; i < index; i++) {
            if(chatList[i] != null) {
                mainPanel.add(chatList[i]);
            }
        }
    }
    
    private JPanel getPanel() {
        JPanel msjpanel = new JPanel();
        msjpanel.setPreferredSize(new Dimension(600, 100));
        msjpanel.setBorder(new LineBorder(Color.WHITE, 10, true));
        return msjpanel;
    }
    
    public void eliminarPanel(int panelId){
        for (int i = 0; i < index; i++) {
            if( chatList[i] != null ) {
                JPanel panel = chatList[i];
                if(panel.getName().equals("panel-"+panelId)) {
                     chatList[i] = null;
                } else {
                    chatList[i] = panel;
                }
            }
        }
        this.renderMainPanel(null);
    }
    
    public void setMensaje(String text) {
        this.setMensaje(text, null);
    }
    
    public void setMensaje(String text, String user) {
        JPanel msjpanel = this.getPanel();
        
        JLabel imgLabel = new JLabel();
        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 70));
        textArea.setLineWrap(true);
        
        if(user == "bot") {
            msjpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            msjpanel.setBackground(getColorBot());
            textArea.setBackground(getColorBot());
            imgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/robot-icon.png")));
            msjpanel.add(imgLabel);
            textArea.setText(text);
            msjpanel.add(textArea);
        } else {
            msjpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            msjpanel.setBackground(getColorUsuario());
            textArea.setBackground(getColorUsuario());
            imgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person-icon.png")));
            textArea.setText(text);
            msjpanel.add(textArea);
            msjpanel.add(imgLabel);
        }
        
        this.renderMainPanel(msjpanel);
    }
    
    public void crearTktOpcion(Ventanas.chat chatFrame) {
        int panelId = index;
        JPanel msjpanel = this.getPanel();
        msjpanel.setBackground(getColorBot());
        
        JButton btnSi = new JButton();
        JButton btnNo = new JButton();
        btnSi.setText("SI");
        btnNo.setText("No");
        btnSi.setPreferredSize(new Dimension(80, 25));
        btnNo.setPreferredSize(new Dimension(80, 25));
        
        btnSi.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aqui voy a crear un tickete");
            } 
        });
        
        btnNo.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                eliminarPanel(panelId);
                eliminarPanel(panelId+1);
                chatFrame.render(getMensajesPanel());
            } 
        });
        
        msjpanel.add(btnSi);
        msjpanel.add(btnNo);
        
        this.setMensaje("Desea crear un tiquete al equipo de soporte tecnico?", "bot");
        this.renderMainPanel(msjpanel);
    }
            
    public JPanel getMensajesPanel(){
        return mainPanel;
    }
}
