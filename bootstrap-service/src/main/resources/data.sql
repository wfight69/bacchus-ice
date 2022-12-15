insert into davada_erp_db.beverage_container (beverage_container_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, beverage_container_code, beverage_container_name, beverage_container_type, bottle_purchase_deposit, bottle_purchase_fees, bottle_return_call_service_fees, bottle_return_self_service_fees, bottle_selling_deposit, bottles_in_box, brewing_type, container_purchase_deposit, container_purchase_fees, container_return_call_service_fees, container_return_self_service_fees, container_selling_deposit, description, measurement, stock_quantity, supplier_uuid, version, volume, wholesaler_uuid)
values  ('1ed33fe8-ae04-68bb-8135-25ab696a4d36', '127.0.0.1:50322', 1663140386299, 'bjsong', null, null, null, false, '127.0.0.1:53988', 1664197397009, 'bjsong', 'BC001', '맥주2P(30)', 'P_BOX', 100.00, 0.00, 19.00, 0.00, 100.00, 30, 'BEER', 2000.00, 0.00, 20.00, 0.00, 2000.00, 'description', 'BOX', null, '1ed33fe3-3fdb-6328-8135-25ab696a4d36', 1, '330', '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.erp_user (erp_user_uuid, erp_user_login_id, password, system_admin, system_login, wholesaler_uuid)
values  ('1ed33fdc-541c-64d7-8135-25ab696a4d36', 'loginId', 'password', 'Y', 'Y', '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.employee (employee_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, car_number, department_code, department_name, description, entry_date, leaving_date, occupational_category, office_duty, office_position, address, address_details, email, foreigner_yn, gender, home_telephone, mobile_phone, zip_code, sales_course_code, sales_course_name, employee_code, employee_name, fcm_device_token, filter_use_yn, sms_send_yn, van_device_serial_no, van_term_cert_key, van_term_no, van_term_serial_no, version, wholesaler_uuid, erp_user_uuid)
values  ('1ed33fdc-541c-64d6-8135-25ab696a4d36', '127.0.0.1:50301', 1663140054743, 'bjsong', null, null, null, false, null, null, null, 'carNumber', 'departmentCode', 'departmentName', 'description', '2020-01-01', null, 'WHOLESALE_RETAIL', 'SALES', 'DEPARTMENT_HEAD', '서울 금천구 가산동', 'addressDetails', 'sales@davada.com', 'N', 'MALE', '021231234', '01012341234', 'zipCode', 'salesCourseCode', 'salesCourseName', 'employeeCode', 'employeeName', 'fcmDeviceToken', 'Y', 'Y', 'vanDeviceSerialNo', 'vanTermCertKey', 'vanTermNo', 'vanTermSerialNo', 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36', '1ed33fdc-541c-64d7-8135-25ab696a4d36');

insert into davada_erp_db.wholesaler (wholesaler_uuid, ars1_070telephone, ars1_070telephone_password, ars1_telephone, ars_company, ars_id, ars_main_telephone, ars_ment_use, callchng_end_date, callchng_start_date, password, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, business_number, charge_email, charge_fax, charge_name, charge_telephone, company_type, email, fax, wholesaler_sub_name, telephone, corporation_number, industry_type, jongmok, land_address, land_address_details, latitude, longitude, province, road_address, road_address_details, zip_code, alarm_cycle, alarm_main_telephone, alarm_sub_telephone, alarm_use_yn, filter_exception_msg, filter_normal_msg, filter_use_yn, order_type, sms_send_yn, remarks, reprs_email, reprs_fax, reprs_name, reprs_telephone, service_monthly_amount, service_start_date, uptae, bank_code, van_company, van_credential, van_number, version, wholesaler_code, wholesaler_name, wholesaler_status)
values  ('1ed33fda-d4d1-6be5-8135-25ab696a4d36', 'ars1_070Telephone', 'ars1_070TelephonePassword', 'ars1_Telephone', 'LG', 'arsId', 'arsMainTelephone', 'Y', 'callchngEndDate', 'callchngStartDate', 'password', '127.0.0.1:50298', 1663140014549, 'bjsong', null, null, null, false, null, null, null, 'businessNumber', 'email', 'fax', 'name', '010-3333-4444', 'CORPORATION', 'email', 'fax', 'name', '010-1111-2222', 'corporationNumber', 'ALCOHOLIC', 'jongmok', 'landAddress', 'landAddressDetails', 1, 1, 'SEOUL', 'roadAddress', 'roadAddressDetails', 'zipCode', 'alarmCycle', 'alarmMainTelephone', 'alarmSubTelephone', 'Y', 'filterExceptionMsg', 'filterNormalMsg', 'Y', 'ARS', 'Y', 'remarks', 'email', 'fax', 'name', '010-3333-4444', 10000, '2022-08-01', 'uptae', '1', 'KSNET', '1', '1', 0, '30002', '다바다', 'ACTIVE');

insert into davada_erp_db.supplier (supplier_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, business_number, charge_email, charge_fax, charge_name, charge_telephone, company_type, supply_email, supply_fax, supply_sub_name, supply_telephone, description, employee_uuid, industry_type, jongmok, land_address, land_address_details, latitude, longitude, province, road_address, road_address_details, zip_code, mobile_phone_number, reprs_email, reprs_fax, reprs_name, reprs_telephone, substitution_yn, supplier_code, supplier_name, supplier_status, uptae, version, wholesaler_uuid)
values  ('1ed33fe3-3fdb-6328-8135-25ab696a4d36', '127.0.0.1:50313', 1663140240528, 'bjsong', null, null, null, false, null, null, null, 'businessRegistrationNo', 'wfight69@gmail.com', '02-3445-9089', '담당자', '02-3445-9085', 'CORPORATION', 'wfight69@gmail.com', '02-3445-9089', 'name', '02-3445-9085', 'description', '1ed33fdc-541c-64d6-8135-25ab696a4d36', 'ALCOHOLIC', 'jongmok', '강서구 금낭화로 128', '하이포트 1동 719호', 10, 10, 'SEOUL', 'roadAddress', 'roadAddressDetails', 'zipCode', '010-1234-1234', 'wfight69@gmail.com', '02-3445-9089', '대표자', '02-3445-9085', 'Y', 'supplierCode01', 'supplierName', 'OPEN', 'uptae', 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.purchase_unit_price (purchase_unit_price_uuid, apply_end_date, apply_start_date, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, bottle_price, bottle_subtotal, bottle_vat, container_price, container_subtotal, container_vat, product_uuid, total_price, version)
values  ('1ed33ff0-4c54-6553-8135-25ab696a4d36', '9999-12-31', '2022-01-01', null, null, null, null, null, null, false, null, null, null, 1000.00, 1100.00, 100.00, 30000.00, 33000.00, 3000.00, '1ed33ff0-4c54-6552-8135-25ab696a4d36', 34100.00, 0);

insert into davada_erp_db.selling_unit_price (selling_unit_price_uuid, apply_end_date, apply_start_date, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, ent_bottle_price, ent_bottle_subtotal, ent_bottle_vat, ent_container_price, ent_container_subtotal, ent_container_vat, ent_profit_margin_rate, general_bottle_price, general_bottle_subtotal, general_bottle_vat, general_container_price, general_container_subtotal, general_container_vat, general_profit_margin_rate, product_uuid, version)
values  ('1ed33ff0-4c54-6554-8135-25ab696a4d36', null, null, null, null, null, null, null, null, false, null, null, null, 1000.00, 1100.00, 100.00, 30000.00, 33000.00, 3000.00, 10.00, 1000.00, 1100.00, 100.00, 30000.00, 33000.00, 3000.00, 10.00, '1ed33ff0-4c54-6552-8135-25ab696a4d36', 0);

insert into davada_erp_db.product (product_uuid, alcohol_by_volume, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, barcode, beverage_category, bottle_barcode, bottles_in_box, box_barcode, description, key_product, product_alias_name, product_category, product_code, product_name, product_price_policy, rfid_box_tag, rfid_ea_tag, rfid_product_name, safety_stock, stock_counting_method, stock_quantity, supplier_code, supplier_name, supplier_uuid, version, volume, wholesaler_uuid, beverage_container_uuid, purchase_unit_price_uuid, sales_unit_price_uuid)
values  ('1ed33ff0-4c54-6552-8135-25ab696a4d36', 'alcoholByVolume', '127.0.0.1:50334', 1663140590804, 'bjsong', null, null, null, false, null, null, null, 'barcode', 'SOJU', 'bottleBarcode', 12, 'box', 'description', 'ENABLED', 'productAliasName', 'ALCOHOL', 'productCode01', 'productName', 'ENT_PRICE_POLICY', 'rfidBoxTag', 'rfidEaTag', 'rfidProductName', 100, 'PRODUCT_CONTAINER', 120, 'supplierCode01', 'supplierName', '1ed33fe3-3fdb-6328-8135-25ab696a4d36', 0, 'volume', '1ed33fda-d4d1-6be5-8135-25ab696a4d36', '1ed33fe8-ae04-68bb-8135-25ab696a4d36', '1ed33ff0-4c54-6553-8135-25ab696a4d36', '1ed33ff0-4c54-6554-8135-25ab696a4d36');

insert into davada_erp_db.retail_shop (retail_shop_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, bond_limit_amount, business_category, business_number, charge_email, charge_fax, charge_name, charge_telephone, tax_charge_name, tax_department_name, tax_email, tax_telephone, company_type, retail_shop_email, retail_shop_fax, retail_shop_sub_name, retail_shop_telephone, container_deposit, description, employee_uuid, industry_type, jongmok, license_no, land_address, land_address_details, latitude, longitude, province, road_address, road_address_details, zip_code, mobile_phone_number, reprs_email, reprs_fax, reprs_name, reprs_telephone, retail_shop_code, retail_shop_name, retail_shop_status, service_end_date, service_start_date, uptae, version, wholesaler_uuid)
values  ('1ed33ff6-3891-6125-8135-25ab696a4d36', '127.0.0.1:50340', 1663140749787, 'bjsong', null, null, null, false, null, null, null, 10000000, 'ENT_STORE', '119-13-89653', 'wfight69@gmail.com', '02-3445-9089', '담당자', '02-3445-9085', 'taxChargeName', 'taxDepartmentName', 'taxEmail', 'taxTelephone', 'CORPORATION', 'wfight69@gmail.com', '02-3445-9089', 'name', '02-3445-9085', 'Y', 'description', '1ed33fdc-541c-64d6-8135-25ab696a4d36', 'ALCOHOLIC', 'SW개발', 'licenseNo', '강서구 금낭화로 128', '하이포트 1동 719호', 10, 10, 'SEOUL', 'roadAddress', 'roadAddressDetails', 'zipCode', '010-2270-9085', 'wfight69@gmail.com', '02-3445-9089', '대표자', '02-3445-9085', 'retailShopCode01', '놀부부대찌게_유흥', 'OPEN', '', '2022-01-01', '서비스', 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.retail_order_telephone (retail_order_telephone_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, description, retail_shop_name, retail_shop_uuid, telephone, use_yn, version)
values  ('1ed33ffa-9d5b-6d96-8135-25ab696a4d36', '127.0.0.1:50361', 1663140867735, 'bjsong', null, null, null, false, null, null, null, 'description', '홍길동1', '1ed33ff6-3891-6125-8135-25ab696a4d36', '010-1234-1234', null, 0);

insert into davada_erp_db.retail_order (order_uuid, amount, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, bottle_deposit, bottle_quantity, business_category, calculate_status, container_deposit, container_quantity, description, employee_name, employee_uuid, employee_code, invoice_issue_yn, order_create_date, order_create_time, order_date, order_description, order_time, product_short_name, read_yn, register_order_yn, retail_order_channel, retail_order_status, retail_order_telephone, retail_shop_code, retail_shop_name, retail_shop_uuid, sales_course_code, sales_course_name, sales_type, subtotal_amount, total_amount, vat, vat_yn, version, voice_file_id, warehouse_name, warehouse_uuid, wholesaler_uuid)
values  ('1ed34002-1cf8-6bf8-8135-25ab696a4d36', 0.00, '127.0.0.1:50382', 1663141069019, 'bjsong', null, null, null, false, null, null, null, 0.00, null, null, null, 0.00, null, null, 'employeeName', '1ed33fdc-541c-64d6-8135-25ab696a4d36', null, 'N', null, null, '2022-09-14', 'string', '16:37:49', '', 'N', 'N', 'VOICE', 'RECEIVED', '010-1234-1234', 'retailShopCode01', '놀부부대찌게_유흥', '1ed33ff6-3891-6125-8135-25ab696a4d36', 'salesCourseCode', 'salesCourseName', null, 0.00, 0.00, 0.00, 'N', 0, 'string', null, null, '1ed33fda-d4d1-6be5-8135-25ab696a4d36'),
        ('1ed3402c-d52a-646a-8135-25ab696a4d36', 100000.00, '127.0.0.1:50433', 1663142215765, 'bjsong', null, null, null, false, null, null, null, 0.00, 0, 'ENT_STORE', 'ACTIVE', 30000.00, 0, 'string', 'employeeName', '1ed33fdc-541c-64d6-8135-25ab696a4d36', 'employeeCode', 'Y', '2022-09-10', '14:00:00', '2022-09-10', 'order_description', '12:00:00', 'productName 외 1건', 'Y', 'Y', 'DIRECT', 'ACCEPTED', '010-1234-1234', 'retailShopCode01', '놀부부대찌게_유흥', '1ed33ff6-3891-6125-8135-25ab696a4d36', 'salesCourseCode', 'salesCourseName', 'SALE', 110000.00, 140000.00, 10000.00, 'Y', 0, 'string', 'string', 'string', '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.retail_order_item (order_item_uuid, amount, beverage_container_code, beverage_container_name, beverage_container_uuid, selling_bottle_deposit, selling_container_deposit, bottle_deposit, bottle_quantity, bottles_in_box, container_deposit, container_price, container_subtotal, container_vat, container_quantity, is_contract_price, product_code, product_name, product_uuid, profit_margin_rate, subtotal_amount, total_amount, vat, volume, order_uuid)
values  ('1ed3402c-d531-699b-8135-25ab696a4d36', 100000.00, 'BC001', 'beverageContainerName', '1ed33fe8-ae04-68bb-8135-25ab696a4d36', 100.00, 3000.00, 0.00, 0, 12, 30000.00, 10000.00, 1100.00, 1000.00, 10, false, 'productCode01', 'productName', '1ed33ff0-4c54-6552-8135-25ab696a4d36', 20.00, 110000.00, 140000.00, 10000.00, '360', '1ed3402c-d52a-646a-8135-25ab696a4d36');

insert into davada_erp_db.erp_code_set (code_set_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, description, label, name, type, version, wholesaler_uuid)
values  ('1ed38019-26b6-6418-b04f-59a892b3aba7', '127.0.0.1:50947', 1663581491954, 'bjsong', null, null, null, false, null, null, null, '담당자들 판매코스', '담당자코스', 'COURSE_CODE', 0, 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.erp_code (code_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, code_set_name, code_set_uuid, description, enabled, label, name, ref_code, version, wholesaler_uuid)
values  ('1ed380a5-6a81-6be7-b001-d3e7de90ea2d', '127.0.0.1:51699', 1663585257206, 'bjsong', null, null, null, false, null, null, null, 'COURSE_CODE', '1ed38019-26b6-6418-b04f-59a892b3aba7', 'A코스 입니다.', true, 'A코스', 'COURSE_A', null, 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36'),
        ('1ed380a6-10c2-6c78-b001-d3e7de90ea2d', '127.0.0.1:51699', 1663585274735, 'bjsong', null, null, null, false, null, null, null, 'COURSE_CODE', '1ed38019-26b6-6418-b04f-59a892b3aba7', 'B코스 입니다.', true, 'B코스', 'COURSE_B', null, 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36'),
        ('1ed380a6-8157-6949-b001-d3e7de90ea2d', '127.0.0.1:51699', 1663585286540, 'bjsong', null, null, null, false, null, null, null, 'COURSE_CODE', '1ed38019-26b6-6418-b04f-59a892b3aba7', 'C코스 입니다.', true, 'C코스', 'COURSE_C', null, 0, '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.warehouse (warehouse_uuid, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, description, employee_name, employee_uuid, land_address, land_address_details, latitude, longitude, province, road_address, road_address_details, zip_code, supplier_name, supplier_uuid, telephone, version, warehouse_code, warehouse_name, warehouse_type, wholesaler_uuid)
values  ('1ed398f6-66ca-6b79-90d8-bf75465a4ee1', '127.0.0.1:51299', 1663752357835, 'bjsong', null, null, null, false, null, null, null, 'description', 'employeeName', '1ed33fdc-541c-64d6-8135-25ab696a4d36', '서울 금천구 가산동 60-25', '에이스하이엔드타워6차', 37.476, 126.886, 'SEOUL', '서울특별시 금천구 벚꽃로 234', '에이스하이엔드타워6차', '08513', 'supplierName', '1ed33fe3-3fdb-6328-8135-25ab696a4d36', '02-1234-1234', 0, 'warehouseCode01', 'warehouseName', 'WAREHOUSE', '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.purchase_order (purchase_order_uuid, amount, create_ip, create_timestamp, create_user_id, delete_ip, delete_timestamp, delete_user_id, deleted, update_ip, update_timestamp, update_user_id, bottle_deposit, bottle_quantity, business_number, calculate_status, container_deposit, container_quantity, description, order_date, order_time, product_short_name, purchase_type, subtotal_amount, supplement_quantity, supplier_code, supplier_name, supplier_representative_name, supplier_uuid, total_amount, trading_statement_yn, vat, vat_yn, version, warehouse_name, warehouse_uuid, wholesaler_uuid)
values  ('1ed39904-41ef-690a-90d8-bf75465a4ee1', 100000.00, '127.0.0.1:51314', 1663752729890, 'bjsong', null, null, null, false, null, null, null, 0.00, 0, 'businessRegistrationNo', 'ACTIVE', 30000.00, 0, 'string', '2022-09-20', '10:00:00', 'productName 외 1건', 'PURCHASE', 110000.00, 0, 'supplierCode01', 'supplierName', '대표자', '1ed33fe3-3fdb-6328-8135-25ab696a4d36', 140000.00, 'Y', 10000.00, 'Y', 0, 'warehouseName', '1ed398f6-66ca-6b79-90d8-bf75465a4ee1', '1ed33fda-d4d1-6be5-8135-25ab696a4d36');

insert into davada_erp_db.purchase_order_item (purchase_order_item_uuid, amount, beverage_container_code, beverage_container_name, beverage_container_uuid, selling_bottle_deposit, selling_container_deposit, bottle_deposit, bottle_quantity, bottles_in_box, container_deposit, container_price, container_subtotal, container_vat, container_quantity, product_code, product_name, product_uuid, subtotal_amount, supplement_quantity, total_amount, vat, volume, purchase_order_uuid)
values  ('1ed39904-4207-6fab-90d8-bf75465a4ee1', 100000.00, 'BC001', 'beverageContainerName', '1ed33fe8-ae04-68bb-8135-25ab696a4d36', 100.00, 3000.00, 0.00, 0, 12, 30000.00, 10000.00, 1100.00, 1000.00, 10, 'productCode01', 'productName', '1ed33ff0-4c54-6552-8135-25ab696a4d36', 110000.00, null, 140000.00, 10000.00, '360', '1ed39904-41ef-690a-90d8-bf75465a4ee1');