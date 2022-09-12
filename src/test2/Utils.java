package test2;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static boolean isOverlap(Chessman current, List<Chessman> chessmen) {
        return chessmen.stream()
                .filter(c -> !c.getId().equals(current.getId()))
                .anyMatch(c -> c.getPosition().equals(current.getPosition()));
    }

    public static boolean isDone(List<Chessman> chessmen) {
        List<Chessman> collect = chessmen.stream().filter(c -> c.getState().equals(CommonEnum.State.STOP)).collect(Collectors.toList());
        return collect.size() == chessmen.size();
    }

    public static Chessman initChess(String commandLine) {
        try {
            return new Chessman(commandLine);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
