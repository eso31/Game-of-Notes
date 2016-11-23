/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notetester;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Dilan
 */
public class NoteTester extends Application {

    public int count = 0;
    public int countT = 0;
    public int indexRealNote = 0;
    public String RealNote = "";

    Button btnA = new Button();
    Button btnAS = new Button();
    Button btnB = new Button();
    Button btnC = new Button();
    Button btnCS = new Button();
    Button btnD = new Button();
    Button btnDS = new Button();
    Button btnE = new Button();
    Button btnF = new Button();
    Button btnFS = new Button();
    Button btnG = new Button();
    Button btnGS = new Button();
    Label lb = new Label("\n");
    Label lbcount = new Label("\n");
    Button btnAllN = new Button();
    Button btnNT = new Button();
    Button btnRC = new Button();

    @Override
    public void start(Stage primaryStage) throws LineUnavailableException {

        final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();

        btnAllN.setText("Play All notes");
        btnAllN.setId("font-button");
        btnNT.setText("Next Tone");
        btnRC.setText("Replay Current Tone");
        btnRC.setDisable(true);

        btnA.setText("A");
        btnAS.setText("A#");
        btnB.setText("B");
        btnC.setText("C");
        btnCS.setText("C#");
        btnD.setText("D");
        btnDS.setText("D#");
        btnE.setText("E");
        btnF.setText("F");
        btnFS.setText("F#");
        btnG.setText("G");
        btnGS.setText("G#");

        disableAllNoteBtn();

        btnAllN.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PlayAllNotes(line);
            }
        });

        btnRC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                testNotes(line);
            }
        });

        btnNT.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                btnAllN.setDisable(true);
                btnNT.setDisable(true);
                btnRC.setDisable(false);
                lb.setText("");
                newNote();
                testNotes(line);
                enableAllNoteBtn();
                countT++;
                //lb.setText("Nota Real "+RealNote);
            }
        });

        btnA.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "A");
            }
        });

        btnAS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "A#");
            }
        });

        btnB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "B");
            }
        });

        btnC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "C");
            }
        });

        btnCS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "C#");
            }
        });

        btnD.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "D");
            }
        });

        btnDS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "D#");
            }
        });

        btnE.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "E");
            }
        });

        btnF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "F");
            }
        });

        btnFS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "F#");
            }
        });

        btnG.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "G");
            }
        });

        btnGS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonPressed(line, "G#");
            }
        });

        HBox hNotes = new HBox();
        hNotes.getChildren().add(btnA);
        hNotes.getChildren().add(btnAS);
        hNotes.getChildren().add(btnB);
        hNotes.getChildren().add(btnC);
        hNotes.getChildren().add(btnCS);
        hNotes.getChildren().add(btnD);
        hNotes.getChildren().add(btnDS);
        hNotes.getChildren().add(btnE);
        hNotes.getChildren().add(btnF);
        hNotes.getChildren().add(btnFS);
        hNotes.getChildren().add(btnG);
        hNotes.getChildren().add(btnGS);

        VBox v1 = new VBox();
        HBox h1 = new HBox();
        v1.getChildren().add(btnAllN);
        h1.getChildren().add(btnRC);
        h1.getChildren().add(btnNT);
        v1.getChildren().add(h1);
        v1.getChildren().add(hNotes);
        v1.getChildren().add(lb);
        v1.getChildren().add(lbcount);

        StackPane root = new StackPane();
        root.getChildren().add(v1);

        Scene scene = new Scene(root, 400, 150);

        primaryStage.setTitle("Notes");
        primaryStage.setScene(scene);
        primaryStage.show();
        //scene.getStylesheets().add("something.css");
        //line.drain();
        //line.close();
    }

    public void disableAllNoteBtn() {
        btnA.setDisable(true);
        btnAS.setDisable(true);
        btnB.setDisable(true);
        btnC.setDisable(true);
        btnCS.setDisable(true);
        btnD.setDisable(true);
        btnDS.setDisable(true);
        btnE.setDisable(true);
        btnF.setDisable(true);
        btnFS.setDisable(true);
        btnG.setDisable(true);
        btnGS.setDisable(true);
    }

    public void enableAllNoteBtn() {
        btnA.setDisable(false);
        btnAS.setDisable(false);
        btnB.setDisable(false);
        btnC.setDisable(false);
        btnCS.setDisable(false);
        btnD.setDisable(false);
        btnDS.setDisable(false);
        btnE.setDisable(false);
        btnF.setDisable(false);
        btnFS.setDisable(false);
        btnG.setDisable(false);
        btnGS.setDisable(false);
    }

    public void buttonPressed(SourceDataLine line, String note) {
        disableAllNoteBtn();
        btnAllN.setDisable(false);
        btnRC.setDisable(true);
        btnNT.setDisable(false);
        if (RealNote.equals(note)) {
            count++;
            lb.setText("Correcto!");
        } else {
            lb.setText("Incorrecto. Es " + RealNote);
            testNotesString(line, note);
            testNotes(line);
        }
        double acc = ((double) count / countT) * 100;
        acc = round(acc, 2);
        lbcount.setText(count + "/" + countT + "\nAcuraccy: " + acc + "%");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    enum Note {
        //1	    2  3    4  5     6   7    8   9   10  11   12  13
        REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
        public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
        public static final int SECONDS = 2;
        private byte[] sin = new byte[SECONDS * SAMPLE_RATE];

        Note() {
            int n = this.ordinal();
            if (n > 0) {
                double exp = ((double) n - 1) / 12d;
                double f = 440d * Math.pow(2d, exp);
                for (int i = 0; i < sin.length; i++) {
                    double period = (double) SAMPLE_RATE / f;
                    double angle = 2.0 * Math.PI * i / period;
                    sin[i] = (byte) (Math.sin(angle) * 127f);
                }
            }
        }

        public byte[] data() {
            return sin;
        }
    }

    public void play(SourceDataLine line, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
        int count = line.write(note.data(), 0, length);
    }

    public void PlayAllNotes(SourceDataLine line) {
        for (Note n : Note.values()) {
            play(line, n, 250);
            play(line, Note.REST, 10);
        }
    }

    public void newNote() {
        indexRealNote = (int) (Math.random() * 12);
    }

    public void testNotes(SourceDataLine line) {
        switch (indexRealNote) {
            case 0:
                ptest(line, Note.A4);
                RealNote = "A";
                break;
            case 1:
                ptest(line, Note.A4$);
                RealNote = "A#";
                break;
            case 2:
                ptest(line, Note.B4);
                RealNote = "B";
                break;
            case 3:
                ptest(line, Note.C4);
                RealNote = "C";
                break;
            case 4:
                ptest(line, Note.C4$);
                RealNote = "C#";
                break;
            case 5:
                ptest(line, Note.D4);
                RealNote = "D";
                break;
            case 6:
                ptest(line, Note.D4$);
                RealNote = "D#";
                break;
            case 7:
                ptest(line, Note.E4);
                RealNote = "E";
                break;
            case 8:
                ptest(line, Note.F4);
                RealNote = "F";
                break;
            case 9:
                ptest(line, Note.F4$);
                RealNote = "F#";
                break;
            case 10:
                ptest(line, Note.G4);
                RealNote = "G";
                break;
            case 11:
                ptest(line, Note.G4$);
                RealNote = "G#";
                break;
        }

    }

    public void testNotesString(SourceDataLine line, String guessNote) {
        switch (guessNote) {
            case "A":
                ptest(line, Note.A4);
                break;
            case "A#":
                ptest(line, Note.A4$);
                break;
            case "B":
                ptest(line, Note.B4);
                break;
            case "C":
                ptest(line, Note.C4);
                break;
            case "C#":
                ptest(line, Note.C4$);
                break;
            case "D":
                ptest(line, Note.D4);
                break;
            case "D#":
                ptest(line, Note.D4$);
                break;
            case "E":
                ptest(line, Note.E4);
                break;
            case "F":
                ptest(line, Note.F4);
                break;
            case "F#":
                ptest(line, Note.F4$);
                break;
            case "G":
                ptest(line, Note.G4);
                break;
            case "G#":
                ptest(line, Note.G4$);
                break;
        }

    }

    public void ptest(SourceDataLine line, Note i) {
        play(line, i, 1000);
        play(line, Note.REST, 10);
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
