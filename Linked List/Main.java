package com.company;
import java.util.Scanner;
class pillowPass {
    int direction = 0,curtime = 0;
    Node pillowHolder = null;
    class Node {
        int playerNumber, time ;
        Node previous, next;

        public Node(int playerNumber, int time) {
            this.playerNumber = playerNumber;
            this.time = time;
            previous = next = null;
        }

    }

    Node head=null, tail = null;
    public void insertPos(Node position, int playerNumber, int time) {

        Node newPlayer = new Node(playerNumber,time);
        if(position==null){
            newPlayer.next = head;
            if(head!=null)
                head.previous = newPlayer;
            else
                tail = newPlayer;

            head = newPlayer;
            return;
        }

        newPlayer.previous = position;
        newPlayer.next = position.next;
        if(position.next!=null)
            position.next.previous = newPlayer;
        else
            tail = newPlayer;

        position.next = newPlayer;


    }

    public void deleteNode(Node position)
    {

        if(position==null){
            System.out.println("INVALID POSITION");
        }

        if(head == position){
            head = head.next;
            head.previous = null;
            return;
        }

        if(tail == position){
            tail = tail.previous;
            tail.next = null;
            return;
        }

        position.previous.next = position.next;
        position.next.previous = position.previous;

    }

    public void printNodes() {
        Node cur = pillowHolder;
        while (true){
            System.out.print(cur.playerNumber);
            if(direction == 0){
                cur = cur.next;
                if(cur == null)cur = head;
            }
            else {
                cur = cur.previous;
                if(cur==null)cur = tail;
            }
            if(cur == pillowHolder)break;
            System.out.print(", ");
        }

    }
    public Node holder(int atSec) {
        int left = atSec-curtime;
        while (pillowHolder.time<left){
            left-=pillowHolder.time;
            if(direction == 0){
                if(pillowHolder.next==null){

                    pillowHolder = head;
                }
                else{
                    pillowHolder = pillowHolder.next;
                }
            }
            else{
                if(pillowHolder.previous==null){
                    pillowHolder = tail;
                }
                else{
                    pillowHolder = pillowHolder.previous;
                }
            }
        }
        curtime = atSec-left;
        return pillowHolder;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pillowPass game = new pillowPass();
        int player = sc.nextInt();
        for (int i = 0; i < player; i++)
            game.insertPos(game.tail, i + 1, sc.nextInt());

        boolean gameOver = false;
        game.pillowHolder = game.head;
        while (true) {
            int required = sc.nextInt();

            char op = sc.next().charAt(0);

            if (gameOver) {
                if (op == 'I') sc.nextInt();
                if (op == 'F') break;
                continue;
            }


            pillowPass.Node holder = game.holder(required);

            if (op == 'P') {
                System.out.println("Player " + holder.playerNumber + " is holding the pillow at t=" + required);
            } else if (op == 'I') {
                if (game.direction == 0) {
                    game.insertPos(holder.previous, ++player, sc.nextInt());
                } else {
                    game.insertPos(holder, ++player, sc.nextInt());
                }
            } else if (op == 'M') {
                System.out.println("Player " + holder.playerNumber + " has been eliminated at t=" + required);
                pillowPass.Node newHolder;
                if (game.direction == 0) {
                    newHolder = holder.next;
                    if (newHolder == null) newHolder = game.head;
                } else {
                    newHolder = holder.previous;
                    if (newHolder == null) newHolder = game.tail;
                }
                game.deleteNode(holder);
                game.pillowHolder = newHolder;
                game.curtime = required;

                if (game.head == game.tail) {
                    System.out.println("Game over: Player " + game.pillowHolder.playerNumber + " wins!!");
                    gameOver = true;
                }
            } else if (op == 'R') {
                game.direction = 1 - game.direction;

            } else {
                if (game.head == game.tail) {
                    System.out.println("Game over: Player " + game.pillowHolder.playerNumber + " wins!!");
                    break;
                }
                System.out.println("Game over: Player " + game.pillowHolder.playerNumber + " is holding the pillow at t=" + required +
                        ". Pillow passing sequence = Player ");
                game.printNodes();
                break;
            }

        }
    }
}
