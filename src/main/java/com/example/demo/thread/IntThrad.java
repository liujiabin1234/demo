package com.example.demo.thread;

class IntThrad extends Thread {
    private Res res;

    public IntThrad(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (res) {
                if (res.flag) {
                    try {
                        // 当前线程变为等待，但是可以释放锁
                        res.wait();
                    } catch (Exception e) {

                    }
                }
                if (count == 0) {
                    res.userName = "余胜军";
                    res.userSex = "男";
                } else {
                    res.userName = "小紅";
                    res.userSex = "女";
                }
                count = (count + 1) % 2;

            }

        }
    }
}



