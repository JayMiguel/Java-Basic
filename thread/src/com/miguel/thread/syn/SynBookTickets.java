package com.miguel.thread.syn;

import java.util.ArrayList;
import java.util.List;

public class SynBookTickets {

    public static void main(String[] args) {
        List<Integer> seats = new ArrayList<>(); //剩余座位
        for (int i = 1; i <= 5; i++) {
            seats.add(i);
        }
        Cinema cinema = new Cinema(seats, "HappyHour");

        List<Integer> tickets1 = new ArrayList<>(); //客户1订购的座位
        tickets1.add(1);
        tickets1.add(2);
        List<Integer> tickets2 = new ArrayList<>(); //客户2订购的座位
        tickets2.add(5);
        tickets2.add(6);

        new Thread(new Customer(tickets1, cinema)).start();
        new Thread(new Customer(tickets2, cinema)).start();
    }

}

class Customer implements Runnable {

    private List<Integer> tickets;
    private Cinema cinema;

    public Customer(List<Integer> tickets, Cinema cinema) {
        this.tickets = tickets;
        this.cinema = cinema;
    }

    @Override
    public void run() {
        boolean flag = cinema.bookSeats(tickets);
        if (flag)
            System.out.println(Thread.currentThread().getName() + "-->出票成功，座位号为：" + tickets);
        else
            System.out.println(Thread.currentThread().getName() + "-->出票失败，余票不足");
    }
}

class Cinema {
    private List<Integer> seats;
    private String name;

    public Cinema(List<Integer> seats, String name) {
        this.seats = seats;
        this.name = name;
    }

    public boolean bookSeats(List<Integer> tickets) {
        //判断是否有票
        if (seats.size() <= 0) {
            return false;
        }
        synchronized (this) {
            //显示剩余座位
            System.out.println("欢迎光临" + this.name + ",目前剩余座位号：" + seats);
            //备份剩余座位
            List<Integer> temp = new ArrayList<>();
            temp.addAll(seats);
            //去除客户订购的座位
            temp.removeAll(tickets);
            //判断客户订购的座位是否对应剩余座位
            if (seats.size() - temp.size() != tickets.size()) {
                return false;
            }
            seats = temp;
            return true;
        }
    }
}
