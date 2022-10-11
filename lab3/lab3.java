package lab3;
import java.util.Scanner;
public class lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameControl gc;
        String buf_name1;
        String buf_name2;

        do {
            System.out.print("Введите имя первого игрока: ");
            buf_name1 = scanner.next();
            System.out.print("Введите имя второго игрока: ");
            buf_name2 = scanner.next();

            gc = new GameControl(buf_name1, buf_name2);

            System.out.println("Создать новую игру? (+/-)");
        } while (scanner.next().equals("+"));
    }
}

class GameControl {
    private final char x = 'x';
    private final char o = 'o';
    private boolean stop;
    private int cord;
    private int step_count = 0;

    private Scanner scanner;
    private Play_Field pf;
    private Player player1;
    private Player player2;

    public GameControl(String name1, String name2) {
        scanner = new Scanner(System.in);
        pf = new Play_Field();
        player1 = new Player(pf, x, name1);
        player2 = new Player(pf, o, name2);

        GameRun();
    }

    private void GameRun() {
        do{
            pf.Restart();
            stop = false;
            GameControl.Rule();
            do{
                System.out.print("Ход "+player1.name+" (введите число от 1 до 9 включительно): ");

                do {
                    cord = scanner.nextInt();
                    while (!(cord < 10 && cord > 0)) {
                        System.out.print("Введено некорректное число, попробуйте еще раз: ");
                        cord = scanner.nextInt();
                    }
                } while (!(player1.Step(cord-1)));
                step_count++;
                pf.Include_data();

                if(pf.Winner(player1.shape)){
                    player1.Win();
                    player2.Lose();
                    stop = true;
                }
                else if(step_count == 9) {
                    System.out.println("\nНичья");
                    stop = true;
                }
                else {
                    System.out.print("Ход " + player2.name + " (введите число от 1 до 9 включительно): ");

                    do {
                        cord = scanner.nextInt();
                        while (!(cord < 10 && cord > 0)) {
                            System.out.print("Введено некорректное число, попробуйте еще раз: ");
                            cord = scanner.nextInt();
                        }
                    } while (!(player2.Step(cord - 1)));
                    step_count++;
                    pf.Include_data();

                    if (pf.Winner(player2.shape)) {
                        player2.Win();
                        player1.Lose();
                        stop = true;
                    }
                }
            }while (!stop);
            System.out.println("Сыграть еще? (+/-)");
        } while(scanner.next().equals("+"));
    }

    public static void Rule(){
        System.out.println("\nЧтоб указать в какую ячейку сетки сделать свой ход - ориентируйтесь по данной таблице, где цифра соответствует координате ячейки");

        int count = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                if(j == 1 || j == 3) {
                    System.out.print('|');
                }
                else{
                    System.out.print(count++);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Player {
    private Play_Field pf;
    private int count_W = 0;
    private int count_L = 0;
    String name;
    char shape;

    public Player(Play_Field pf, char shp, String name){
        this.pf = pf;
        shape = shp;
        this.name = name;
    }

    public boolean Step(int cord){
        if(pf.place_data[cord] == ' '){
            pf.place_data[cord] = shape;
            return true;
        }
        else{
            System.out.print("Ячейка уже занята, выберите другую :");
            return false;
        }
    }

    public void Win(){
        count_W++;
        System.out.println("\n" + name + " - победил");
        System.out.println("Побед: " + count_W + "    поражений: " + count_L);
    }
    public void Lose(){
        count_L++;
        System.out.println("\n" + name + " - проиграл");
        System.out.println("Побед: " + count_W + "    поражений: " + count_L);
    }
}

class Play_Field {
    char[] place_data = {' ', ' ',' ',' ',' ',' ',' ',' ',' ',};
    char[][] canvas = new char[3][5];


    public void Include_data(){
        int count = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                if(j == 1 || j == 3) {
                    canvas[i][j] = '|';
                }
                else{
                    canvas[i][j] = place_data[count++];
                }
            }
        }
        Print();
    }

    public boolean Winner(char ch){
        if((place_data[0]==ch)&&(place_data[1]==ch)&&(place_data[2]==ch))
            return true;
        else if((place_data[3]==ch)&&(place_data[4]==ch)&&(place_data[5]==ch))
            return true;
        else if((place_data[6]==ch)&&(place_data[7]==ch)&&(place_data[8]==ch))
            return true;
        else if((place_data[0]==ch)&&(place_data[3]==ch)&&(place_data[6]==ch))
            return true;
        else if((place_data[1]==ch)&&(place_data[4]==ch)&&(place_data[7]==ch)) 
            return true;
        else if((place_data[2]==ch)&&(place_data[5]==ch)&&(place_data[8]==ch))
            return true;
        else if((place_data[0]==ch)&&(place_data[4]==ch)&&(place_data[8]==ch))
            return true;
        else if((place_data[2]==ch)&&(place_data[4]==ch)&&(place_data[6]==ch))
            return true;
        else 
            return false;
    }

    public void Restart(){
        canvas = new char[3][5];
        for (int i = 0;i < place_data.length; i++)
            place_data[i] = ' ';
    }

    public void Print(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(canvas[i][j]);
            }
            System.out.println();
        }
    }
}