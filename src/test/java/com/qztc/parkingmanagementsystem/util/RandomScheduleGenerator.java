package com.qztc.parkingmanagementsystem.util;
import java.util.concurrent.ThreadLocalRandom;
/**
 * @author congyijiu
 * @create 2024-04-26-17:52
 */
public class RandomScheduleGenerator {

    public static void main(String[] args) {
        String jsonSchedule = generateRandomSchedule();
        System.out.println(jsonSchedule);
    }

    public static String generateRandomSchedule() {
        StringBuilder jsonBuilder = new StringBuilder("{");

        String[] daysOfWeek = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            String day = daysOfWeek[i];
            String startTime = generateRandomTime();
            String endTime = generateRandomTime();
            jsonBuilder.append("\"").append(day).append("\": {\"start\": \"").append(startTime)
                    .append("\", \"end\": \"").append(endTime).append("\"}");

            if (i < daysOfWeek.length - 1) {
                jsonBuilder.append(", ");
            }
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static String generateRandomTime() {
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);
        int second = ThreadLocalRandom.current().nextInt(0, 60);

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
