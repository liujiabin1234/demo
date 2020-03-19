package com.example.demo.thread;

class OutThread extends Thread {
    private Res res;

    public OutThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                System.out.println(res.userName + "--" + res.userSex);
                res.flag = true;
                // 唤醒当前线程
                res.notify();
            }
        }
    }
}


