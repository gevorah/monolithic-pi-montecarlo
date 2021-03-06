package monolithic.pi.montecarlo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements Runnable {

    private GUI gui;
    private Montecarlo montecarlo;

    public GUIController() {
        montecarlo = new Montecarlo();
    }

    @Override
    public void run() {
        try {
            gui = new GUI();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        events();
    }

    public void update(String result) {
        String[] show = result.split(";");

        System.out.println(result);
        
        gui.getTable().addRow(show);
    }

    public void events() {
        gui.getCompute().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gui.getPoints().getText().isBlank() && !gui.getSeed().getText().isBlank() && !gui.getEpsilon().getText().isBlank()) {
                    int targetPointsExponent = Integer.parseInt(gui.getPoints().getText());
                    int epsilonExp = Integer.parseInt(gui.getEpsilon().getText());
                    int seed = Integer.parseInt(gui.getSeed().getText());

                    update(montecarlo.computePi(targetPointsExponent, epsilonExp, seed));
                }
            }
        });

        gui.getDoExperiment().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gui.getExperiments().getSelectedItem().toString().equals("-Select-")) {
                    int targetPointsExponent = Integer.parseInt(gui.getExperiments().getSelectedItem().toString().split("e")[1]);
                    
                    for(int i=0; i<10; i++){
                        update(montecarlo.computePi(targetPointsExponent, 0, 42));
                    }
                }
            }
        });
    }
    
}
