package test2;

import java.util.*;

public class Chessman {
    public static final int MATRIX_MAX = 10;
    public static final int MATRIX_MIN = 0;

    private UUID id;
    private Position position;
    private CommonEnum.Direction direction;
    private CommonEnum.State state;
    private Queue<String> steps;

    public CommonEnum.State getState() {
        return state;
    }

    public UUID getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public Chessman(String commandLine) throws Exception {
        String[] init = commandLine.split(";");
        validate(init);
        initPosition(init[0]);
        initSteps(init[1]);
        id = UUID.randomUUID();
        this.state = CommonEnum.State.READY;
        System.out.println("Initialized a chessman: " + this);
    }

    public void doStep() {
        if (this.state == CommonEnum.State.STOP)
            return;
        String nextStep = steps.poll();
        if (nextStep == null) {
            this.state = CommonEnum.State.STOP;
            return;
        }
        switch (CommonEnum.Steps.valueOf(nextStep)) {
            case L:
                this.left();
                break;
            case R:
                this.right();
                break;
            case M:
                this.move();
                break;
        }
    }

    public void back() {
        switch (this.direction) {
            case N:
                this.position.x--;
                break;
            case W:
                this.position.y++;
                break;
            case S:
                this.position.x++;
                break;
            case E:
                this.position.y--;
                break;
        }
    }

    private void move() {
        switch (this.direction) {
            case N:
                if (this.position.x == MATRIX_MAX)
                    break;
                this.position.x++;
                break;
            case W:
                if (this.position.y == MATRIX_MIN)
                    break;
                this.position.y--;
                break;
            case S:
                if (this.position.x == MATRIX_MIN)
                    break;
                this.position.x--;
                break;
            case E:
                if (this.position.y == MATRIX_MAX)
                    break;
                this.position.y++;
                break;
        }
    }

    private void left() {
        switch (this.direction) {
            case N:
                this.direction = CommonEnum.Direction.W;
                break;
            case W:
                this.direction = CommonEnum.Direction.S;
                break;
            case S:
                this.direction = CommonEnum.Direction.E;
                break;
            case E:
                this.direction = CommonEnum.Direction.N;
                break;
        }
    }

    private void right() {
        switch (this.direction) {
            case N:
                this.direction = CommonEnum.Direction.E;
                break;
            case E:
                this.direction = CommonEnum.Direction.S;
                break;
            case S:
                this.direction = CommonEnum.Direction.W;
                break;
            case W:
                this.direction = CommonEnum.Direction.N;
                break;
        }
    }

    private void initSteps(String commandLine) {
        this.steps = new LinkedList<>(Arrays.asList(commandLine.split("")));

    }

    private void initPosition(String commandLine) {
        String[] command = commandLine.split("");
        this.position = new Position(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        this.direction = CommonEnum.Direction.valueOf(command[3]);
    }

    private void validate(String[] init) throws Exception {
        if (init.length != 2
                || !"I".equals(init[0].substring(0, 1))
                || init[0].length() != 4) {
            throw new Exception("Invalid command");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", position=" + position +
                ", direction=" + direction +
                '}';
    }

    static class Position {
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public String toString() {
            return "[" + x + ";" + y + ']';
        }
    }
}
