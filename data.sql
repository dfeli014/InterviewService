rollback;
begin transaction;
/*Begin Dropping Tables*/
	/*Level 2 Tables*/
		drop table if exists interview;
	/*Level 1 Tables*/
		drop table if exists interview_feedback;
		drop table if exists associate_input;
	/*Level 0 Tables*/
		drop table if exists client;
		drop table if exists feedback_status;
		drop table if exists interview_format;
/*End Dropping Tables*/

/*Begin Creating Tables*/
	/*Level 0 Tables*/
		create table feedback_status (
			feedback_status_id               serial    not null unique,
			feedback_status_description      text      not null unique,
			constraint pk_feedback_status    primary key (feedback_status_id)
		);
		create table interview_format (
			interview_format_id              serial    not null unique,
			interview_format_description     text      not null unique,
			constraint pk_interview_format   primary key (interview_format_id)
		);
		create table client(
			client_id                        serial    not null unique,
			client_name                      text      not null unique,
			constraint pk_client             primary key (client_id)
		);
	/*Level 1 Tables*/
		create table interview_feedback (
			interview_feedback_id            serial    not null unique,
			feedback_requested               timestamp not null,
			feedback                         text      not null,
			feedback_received                timestamp not null,
			feedback_delivered               timestamp,
			feedback_status                  integer   not null,
			constraint pk_interview_feedback primary key (interview_feedback_id),
			constraint fk_feedback_status    foreign key (feedback_status) references feedback_status (feedback_status_id)
		);
		create table associate_input (
			associate_input_id               serial    not null unique,
			received_notifications           timestamp not null,        --Assoc says that the manager told the assoc at ${X} that they have an interview 
			description_provided             boolean   not null,
			interview_format                 integer   not null,
			proposed_format                  integer   not null,
			constraint pk_associate_input    primary key (associate_input_id),
			constraint fk_interview_format   foreign key (interview_format) references interview_format (interview_format_id),
			constraint fk_proposed_format    foreign key (proposed_format) references interview_format (interview_format_id)
		);
	/*Level 2 Tables*/
		create table interview (
			interview_id                     serial    not null unique,
			manager_email                    text      not null,
			associate_email                  text      not null,
			place                            text      not null,
			scheduled                        timestamp not null,        --Manager told the assoc they have an interview scheduled for ${X}
			notified                         timestamp,                 --Manager says they told the assoc at ${X} that they have an interview
			reviewed                         timestamp,                 --Manager reviewed the interview information at ${X}
			interview_feedback               integer,
			associate_input                  integer,
			client                           integer   not null,
			constraint interview_id          primary key (interview_id),
			constraint fk_client             foreign key (client) references client (client_id),
			constraint fk_interview_feedback foreign key (interview_feedback) references interview_feedback (interview_feedback_id),
			constraint fk_associate_input    foreign key (associate_input) references associate_input (associate_input_id)
		);
/*End Creating Tables*/

/*Begin Insert Data*/
	/*feedback_status*/
		insert into feedback_status (feedback_status_description) values ('Pending');
		insert into feedback_status (feedback_status_description) values ('No Feedback');
		insert into feedback_status (feedback_status_description) values ('Selected for Second Round');
		insert into feedback_status (feedback_status_description) values ('Direct Hire');
		insert into feedback_status (feedback_status_description) values ('Selected');
	/*interview_format*/
		insert into interview_format (interview_format_description) values ('On Site');
		insert into interview_format (interview_format_description) values ('In Person');
		insert into interview_format (interview_format_description) values ('Video Call');
		insert into interview_format (interview_format_description) values ('Phone Call');
	/*client*/
		insert into client (client_name) values ('Dell');
		insert into client (client_name) values ('Hewlett Packard');
	/*interview_feedback*/
		insert into interview_feedback (feedback_requested, feedback, feedback_received, feedback_delivered, feedback_status)
			values ('2019-03-01 13:00:00', 'Solid interview.', '2019-03-02 14:00:00', '2019-03-03 15:00:00', 1);
		insert into interview_feedback (feedback_requested, feedback, feedback_received, feedback_delivered, feedback_status)
			values ('2019-03-02 13:00:00', 'Solid interview.', '2019-03-03 14:00:00', '2019-03-04 15:00:00', 2);
		insert into interview_feedback (feedback_requested, feedback, feedback_received, feedback_delivered, feedback_status)
			values ('2019-03-03 13:00:00', 'Solid interview.', '2019-03-04 14:00:00', '2019-03-05 15:00:00', 3);
		insert into interview_feedback (feedback_requested, feedback, feedback_received, feedback_delivered, feedback_status)
			values ('2019-03-04 13:00:00', 'Solid interview.', '2019-03-05 14:00:00', '2019-03-06 15:00:00', 4);
		insert into interview_feedback (feedback_requested, feedback, feedback_received, feedback_delivered, feedback_status)
			values ('2019-03-05 13:00:00', 'Solid interview.', '2019-03-06 14:00:00', '2019-03-07 15:00:00', 5);
	/*associate_input*/
		insert into associate_input (received_notifications, description_provided, interview_format, proposed_format)
			values ('2019-02-28 14:00:00', true, 1, 1);
		insert into associate_input (received_notifications, description_provided, interview_format, proposed_format)
			values ('2019-02-27 12:15:00', true, 2, 2);
		insert into associate_input (received_notifications, description_provided, interview_format, proposed_format)
			values ('2019-02-27 12:00:00', true, 3, 3);
		insert into associate_input (received_notifications, description_provided, interview_format, proposed_format)
			values ('2019-03-04 12:00:00', true, 1, 4);
	/*Interview*/
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('blake.kruppa@revature.com', 'abatson94@gmail.com', 'USF', '2019-02-28 12:00:00', '2019-02-28 14:00:00', '2019-03-01 16:00:00', 1, 1, 1);
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('blake.kruppa@revature.com', 'kenneth.james.currie@gmail.com', 'USF', '2019-02-28 12:00:00', '2019-02-28 09:30:00', '2019-03-02 16:00:00', 2, 2, 1);
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('blake.kruppa@revature.com', 'dfeli014@fiu.edu', 'Reston', '2019-02-28 12:00:00', '2019-02-27 14:00:00', '2019-03-03 16:00:00', 3, 3, 1);
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('abatson94@gmail.com', 'mohamedwomar21@gmail.com', 'Reston', '2019-02-28 12:00:00', '2019-02-28 14:00:00', '2019-03-04 16:00:00', 4, 4, 2);
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('abatson94@gmail.com', 'goncalvesjohnp@gmail.com', 'USF', '2019-02-28 12:00:00', '2019-02-27 12:00:00', null, 5, null, 2);
		insert into interview (manager_email, associate_email, place, scheduled, notified, reviewed, interview_feedback, associate_input, client)
			values ('abatson94@gmail.com', 'loricodes@gmail.com', 'USF', '2019-02-28 12:00:00', '2019-02-28 14:00:00', null, null, null, 2);
/*End Insert Data*/

/*Begin Role Permissions*/
	GRANT ALL ON TABLE public.associate_input TO aws_mike;
	GRANT ALL ON TABLE public.feedback_status TO aws_mike;
	GRANT ALL ON TABLE public.client TO aws_mike;
	GRANT ALL ON TABLE public.interview TO aws_mike;
	GRANT ALL ON TABLE public.interview_feedback TO aws_mike;
	GRANT ALL ON TABLE public.interview_format TO aws_mike;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_mike;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_mike;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_mike;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_mike;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_mike;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_mike;

	GRANT ALL ON TABLE public.associate_input TO aws_chris;
	GRANT ALL ON TABLE public.feedback_status TO aws_chris;
	GRANT ALL ON TABLE public.client TO aws_chris;
	GRANT ALL ON TABLE public.interview TO aws_chris;
	GRANT ALL ON TABLE public.interview_feedback TO aws_chris;
	GRANT ALL ON TABLE public.interview_format TO aws_chris;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_chris;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_chris;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_chris;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_chris;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_chris;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_chris;

	GRANT ALL ON TABLE public.associate_input TO aws_kenneth;
	GRANT ALL ON TABLE public.feedback_status TO aws_kenneth;
	GRANT ALL ON TABLE public.client TO aws_kenneth;
	GRANT ALL ON TABLE public.interview TO aws_kenneth;
	GRANT ALL ON TABLE public.interview_feedback TO aws_kenneth;
	GRANT ALL ON TABLE public.interview_format TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_kenneth;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_kenneth;

	GRANT ALL ON TABLE public.associate_input TO aws_peter;
	GRANT ALL ON TABLE public.feedback_status TO aws_peter;
	GRANT ALL ON TABLE public.client TO aws_peter;
	GRANT ALL ON TABLE public.interview TO aws_peter;
	GRANT ALL ON TABLE public.interview_feedback TO aws_peter;
	GRANT ALL ON TABLE public.interview_format TO aws_peter;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_peter;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_peter;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_peter;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_peter;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_peter;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_peter;

	GRANT ALL ON TABLE public.associate_input TO aws_dom;
	GRANT ALL ON TABLE public.feedback_status TO aws_dom;
	GRANT ALL ON TABLE public.client TO aws_dom;
	GRANT ALL ON TABLE public.interview TO aws_dom;
	GRANT ALL ON TABLE public.interview_feedback TO aws_dom;
	GRANT ALL ON TABLE public.interview_format TO aws_dom;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_dom;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_dom;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_dom;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_dom;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_dom;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_dom;

	GRANT ALL ON TABLE public.associate_input TO aws_mileena;
	GRANT ALL ON TABLE public.feedback_status TO aws_mileena;
	GRANT ALL ON TABLE public.client TO aws_mileena;
	GRANT ALL ON TABLE public.interview TO aws_mileena;
	GRANT ALL ON TABLE public.interview_feedback TO aws_mileena;
	GRANT ALL ON TABLE public.interview_format TO aws_mileena;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_mileena;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_mileena;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_mileena;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_mileena;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_mileena;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_mileena;

	GRANT ALL ON TABLE public.associate_input TO aws_ben;
	GRANT ALL ON TABLE public.feedback_status TO aws_ben;
	GRANT ALL ON TABLE public.client TO aws_ben;
	GRANT ALL ON TABLE public.interview TO aws_ben;
	GRANT ALL ON TABLE public.interview_feedback TO aws_ben;
	GRANT ALL ON TABLE public.interview_format TO aws_ben;
	GRANT ALL ON SEQUENCE public.associate_input_associate_input_id_seq TO aws_ben;
	GRANT ALL ON SEQUENCE public.client_client_id_seq TO aws_ben;
	GRANT ALL ON SEQUENCE public.feedback_status_feedback_status_id_seq TO aws_ben;
	GRANT ALL ON SEQUENCE public.interview_feedback_interview_feedback_id_seq TO aws_ben;
	GRANT ALL ON SEQUENCE public.interview_format_interview_format_id_seq TO aws_ben;
	GRANT ALL ON SEQUENCE public.interview_interview_id_seq TO aws_ben;
	/*End Role Permissions*/
commit; 
