
package controller;

import bean.Bean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Expense;
import view.HomeJPanel;
import view.NewExpenseJframe;
import view.Report;
import view.TransactionJPanel;
import controller.PanelSwitcher;
import view.IncomePanelTransaction;

/**
 *
 * @author DELL
 */
public class ChangeScreenController implements PanelSwitcher{
    private JPanel root;
    private String kindSelected = "";
    private List<Bean> listItem = null;
    
    
    public ChangeScreenController(JPanel jpnRoot){
        this.root = jpnRoot;
    }
    
    public void setView (JPanel jpnItem, JLabel jlbItem){
        kindSelected = "Home";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new HomeJPanel());
        root.validate();
        root.repaint();
        
    }
    
    public void setEvent(List<Bean> listItem){
        this.listItem = listItem;
        for(Bean item: listItem){
           item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(),item.getJlb()));
       }
    }
    
    
    // dùng interface
    @Override
        public void switchToPanel(String panelName) {
            JPanel node = null;
            switch (panelName) {
                case "Home":
                    node = new HomeJPanel();
                    break;
                case "Transaction":
                    node = new TransactionJPanel();
                    break;
                case "Report":
                    node = new Report();
                    break;
                case "Income":
                    node = new IncomePanelTransaction();
                    break;
                default:
                    node = new HomeJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
        }

    
    class LabelEvent implements MouseListener{
        
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        

        @Override
        public void mouseClicked(MouseEvent e) {
            if ("NewExpense".equals(kind)) {
                new NewExpenseJframe(ChangeScreenController.this).setVisible(true);
                return;
            }
            switchToPanel(kind);
            
//            switch(kind){
//                case "Home":
//                    node = new HomeJPanel();
//                    break;
//                case "Transaction":
//                    node = new TransactionJPanel();
//                    break;
////                case "NewExpense":
//////                    node = new NewExpense();
////                    node = null;
////                    new NewExpenseJframe().setVisible(true);
////                    break;
//                case "Report":
//                    node = new Report();
//                    break;
//                default:
//                    node = new HomeJPanel();
//                    break;
//                
//            }
//            root.removeAll();
//            root.setLayout(new BorderLayout());
//            root.add(node);
//            root.validate();
//            root.repaint();
//            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));        
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(76,176,80));
                jlbItem.setBackground(new Color(76,176,80));
            }
        }
        
    }
    
    private void setChangeBackground(String kind){
        for(Bean item: listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(96,100,191));
                item.getJlb().setBackground(new Color(96,100,191));
            }else{
                item.getJpn().setBackground(new Color(76,176,80));
                item.getJlb().setBackground(new Color(76,176,80));
            }
        }
    }
}
