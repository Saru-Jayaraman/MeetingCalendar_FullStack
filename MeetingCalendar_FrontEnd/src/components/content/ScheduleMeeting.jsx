import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { FormProvider, useForm } from 'react-hook-form';
import AlertMessage from './AlertMessage';
import MeetingForm from './MeetingForm';
import { FaCalendarAlt, FaCheckCircle } from "react-icons/fa";

const ScheduleMeeting = () => {
    const methods = useForm();
    let [meetingFormData, setMeetingFormData] = useState({
        title: "",
        date: "",
        startTime: "",
        endTime: "",
        level: "",
        participants: "",
        description: ""
    });
    const [allMeetingsData, setAllMeetingsData] = useState([]);
    const [showAlert, setShowAlert] = useState(false);
    const [alertName, setAlertName] = useState('');
    const [alertColor, setAlertColor] = useState('');
    const [editId, setEditId] = useState();
    const [reload, setReload] = useState(false);
    const [showEdit, setShowEdit] = useState(false);
    const apiEndpoint = "http://localhost:8080/api/meetings";
    const handleCreateButton = () => {// showing create button
        setShowEdit(false);
    };
    const handleEditButton = () => {// showing edit button
        setShowEdit(true);
    };

    const handleCreateMeeting = () => {
        createMeetingAPICall();
        clearFields();
        postAPIOperation("CREATED", "success");
    };

    const handleUpdateMeeting = () => {
        updateMeetingAPICall();
        handleCreateButton();
        clearFields();
        postAPIOperation("UPDATED", "warning");
    };
    
    const clearFields = () => {
        setMeetingFormData({
            title: "",
            date: "",
            startTime: "",
            endTime: "",
            level: "",
            participants: "",
            description: ""
        });
    };

    const postAPIOperation = (name, color) => {
        setAlertName(name);
        setAlertColor(color);
        setShowAlert(true);
    };

    const fetchAllMeetingsAPICall = async () => {
        console.log("Step1: Request FETCH ALL...");
        await axios.get(apiEndpoint)
            .then(response => {
                console.log("Step2: Response FETCH ALL...");
                if(response.status === 200) {
                    console.log(response.data);
                    setAllMeetingsData(response.data);
                } else {
                    console.log("Unexpected response status...", response.status);
                }
            })
            .catch(error => {
                console.log("Error on fetching meeting details...", error);
        });
        console.log("Step3: Finish FETCH ALL...");
    };

    const createMeetingAPICall = async () => {
        try {
            const response = await axios.post(apiEndpoint, meetingFormData);
            if(response.status === 201) {
                console.log("Meeting added successfully...");
                console.log("Response: ", response.data);
                setReload(!reload);
            }
        } catch(error) {
            console.log("Error on creating meeting...", error);
        }
    };

    const updateMeetingAPICall = async () => {
        try {
            const response = await axios.put(`${apiEndpoint}/${editId}`, meetingFormData);
            if(response.status === 204) {
                console.log("Meeting updated successfully...");
                setReload(!reload);
            }
        } catch(error) {
            console.log("Error on updating meeting...", error);
        }
    };

    useEffect(() => {
        fetchAllMeetingsAPICall();
    }, [reload]);

    return (
    <div className='container-fluid bg-light'>
        <div className='row'>
            <div className='col'>
                {showAlert && <AlertMessage icon={<FaCheckCircle />} 
                    message={<>Meeting is successfully <b>{alertName}</b></>} 
                    color={alertColor} />
                }
            </div>
        </div>

        <div className='row'>
            <div className='col'>
                <FormProvider {...methods}>
                    <div className="card my-2">
                        <div className="card-body">
                            <h5 className="card-title bg-primary ps-1 py-1 rounded text-white"><FaCalendarAlt /> Schedule a New Meeting</h5>
                            <MeetingForm
                                meetingFormData={meetingFormData} setMeetingFormData={setMeetingFormData} 
                                showAlert={showAlert} setShowAlert={setShowAlert}
                                handleCreateMeeting={handleCreateMeeting}
                                handleUpdateMeeting={handleUpdateMeeting}
                                clearFields={clearFields}
                                showEdit={showEdit}
                                handleCreateButton={handleCreateButton} />
                        </div>
                    </div>
                </FormProvider>
            </div>
        </div>
    </div>
    );
};

export default ScheduleMeeting;