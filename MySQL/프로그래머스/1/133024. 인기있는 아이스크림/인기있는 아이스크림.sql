/**
    first_half (shopment_id, flavor, total_order)
**/

-- 코드를 입력하세요
SELECT fh.flavor from first_half fh
order by fh.total_order desc, fh.shipment_id asc;