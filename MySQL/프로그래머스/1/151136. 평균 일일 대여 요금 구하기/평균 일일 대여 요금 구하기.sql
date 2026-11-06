/**
    car_rental_company_car ( car_id, car_type, daily_fee, options )
**/

-- 코드를 입력하세요
SELECT round(sum(daily_fee) / count(*), 0) AS AVERAGE_FEE from CAR_RENTAL_COMPANY_CAR C
WHERE C.car_type = 'SUV';