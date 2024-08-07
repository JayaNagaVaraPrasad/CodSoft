import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private static final int QUESTION_TIME = 10; // Time for each question in seconds
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Timer timer;
    private int timeRemaining;

    private String[][] questions = {
            {"What is the largest planet in our Solar System?", "Jupiter"},
            {"What is the chemical symbol for water?", "H2O"},
            {"Who wrote 'To Kill a Mockingbird'?", "Harper Lee"}
    };

    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JLabel timerLabel;
    private JButton submitButton;

    public QuizApp() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(questionLabel, BorderLayout.NORTH);

        answerField = new JTextField();
        frame.add(answerField, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        timerLabel = new JLabel("Time left: " + QUESTION_TIME + " seconds");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(timerLabel, BorderLayout.NORTH);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        bottomPanel.add(submitButton, BorderLayout.SOUTH);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        startQuiz();
    }

    private void startQuiz() {
        loadQuestion();
        startTimer();
        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex][0]);
            answerField.setText("");
            timeRemaining = QUESTION_TIME;
            timerLabel.setText("Time left: " + timeRemaining + " seconds");
        } else {
            endQuiz();
        }
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time left: " + timeRemaining + " seconds");
                if (timeRemaining <= 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });
        timer.start();
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim();
        String correctAnswer = questions[currentQuestionIndex][1];
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            score++;
        }
        currentQuestionIndex++;
        loadQuestion();
        startTimer();
    }

    private void endQuiz() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score);
        frame.dispose();
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            checkAnswer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApp();
            }
        });
    }
}
