
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'PROFANITYFILTER_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('PROFANITYFILTER_MANAGEMENT','profanityfilter.adminFeature.ManageProfanityfilter.name',1,'jsp/admin/plugins/profanityfilter/ManageWords.jsp','profanityfilter.adminFeature.ManageProfanityfilter.description',0,'profanity-filter',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'PROFANITYFILTER_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('PROFANITYFILTER_MANAGEMENT',1);

