package com.example.qmatic.tolltaxcalculator.config;


import com.example.qmatic.tolltaxcalculator.domain.VehicleType;
import com.example.qmatic.tolltaxcalculator.dto.TimeIntervalFee;
import com.example.qmatic.tolltaxcalculator.dto.TimeIntervalFeeList;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.example.qmatic.tolltaxcalculator.config.Constants.TIME_FEE_TO_PAY_YML;


public class InitialiseData {
    public static List<LocalDate> holidayList = new ArrayList<>();
    //public static List<String> holidayList = new ArrayList<>();
    public static final List<TimeIntervalFee> timeFeeList = new ArrayList<>();

    public static final List<VehicleType> tollFreeVehicle = new ArrayList<>(Arrays.asList(VehicleType.TRACTOR,
            VehicleType.DIPLOMAT, VehicleType.FOREIGN, VehicleType.MILITARY, VehicleType.EMERGENCY, VehicleType.MOTORCYCLE));



    static {
        initialiseHolidayMap();
        initTimeFeeYaml();
    }


    public static void initialiseHolidayMap() {
        {
            int year = LocalDate.now().getYear();

            holidayList.add(LocalDate.of(year, 1, 1));

            holidayList.add(LocalDate.of(year, 3, 28));
            holidayList.add(LocalDate.of(year, 3, 29));

            holidayList.add(LocalDate.of(year, 4, 1));
            holidayList.add(LocalDate.of(year, 4, 30));


            holidayList.add(LocalDate.of(year, 5, 1));
            holidayList.add(LocalDate.of(year, 5, 8));
            holidayList.add(LocalDate.of(year, 5, 9));

            holidayList.add(LocalDate.of(year, 6, 5));
            holidayList.add(LocalDate.of(year, 6, 6));
            holidayList.add(LocalDate.of(year, 6, 21));

            for (int i = 1; i <= 31; i++) {
                holidayList.add(LocalDate.of(year, 7, i));
            }

            holidayList.add(LocalDate.of(year, 11, 1));

            holidayList.add(LocalDate.of(year, 12, 24));
            holidayList.add(LocalDate.of(year, 12, 25));
            holidayList.add(LocalDate.of(year, 12, 26));
            holidayList.add(LocalDate.of(year, 12, 31));

        }


    }
    /*
    This method calls to HolidayAPIService which makes a rest call to external holiday API
    */
    /*
    public static void callHolidayAPI(){
        HolidayAPIResponse response=spiService.response();
        List<Holiday> publicHolidays= response.getHolidays().stream().filter(isPublic->isPublic.getIsPublic()==true).collect(Collectors.toList());
        holidayList=  publicHolidays.stream().map(obj->obj.getDate()).collect(Collectors.toList());
    }*/

    public static void initTimeFeeYaml() {

        try (InputStream in = InitialiseData.class.getResourceAsStream(TIME_FEE_TO_PAY_YML)) {
            Yaml yaml = new Yaml(new Constructor(TimeIntervalFeeList.class));
            TimeIntervalFeeList list = yaml.load(in);

            list.getTimeFeeList()
                    .stream()
                    .map(timeFeeObj -> timeFeeToData(timeFeeObj))
                    .forEach(timeFeeList::add);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static TimeIntervalFee timeFeeToData(TimeIntervalFeeList.TimeFeeObj timeFeeObj) {
        return new TimeIntervalFee()
                .setStartTime(LocalTime.parse(timeFeeObj.getStart()))
                .setEndTime(LocalTime.parse(timeFeeObj.getEnd()))
                .setFee(timeFeeObj.getFee());
    }

}
