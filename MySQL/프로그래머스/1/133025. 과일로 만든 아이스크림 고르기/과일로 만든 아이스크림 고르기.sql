/**
    first_half(shipment_id, flavor, total_order)
    ICECREAM_INFO(flavor, ingredient_type)
**/

-- 코드를 입력하세요
SELECT fh.flavor from first_half fh
join ICECREAM_INFO ii on fh.flavor = ii.flavor
where fh.total_order > 3000 and ii.ingredient_type = 'fruit_based'
order by fh.total_order desc;