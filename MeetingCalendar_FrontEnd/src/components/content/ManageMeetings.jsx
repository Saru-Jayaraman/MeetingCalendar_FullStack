import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { FormProvider, useForm } from 'react-hook-form';
import AlertMessage from './AlertMessage';
import MeetingForm from './MeetingForm';
import MeetingsList from './MeetingsList';
import { FaCalendarAlt, FaCheckCircle } from 'react-icons/fa';

const ManageMeetings = () => {
    const [reload, setReload] = useState(false);

    useEffect(() => {
        fetchAllMeetingsAPICall();
    }, [reload]);
    
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
    const [showEdit, setShowEdit] = useState(false);
    const apiEndpoint = "http://localhost:8080/api/meetings";
    const handleCreateButton = () => {// showing create button
        setShowEdit(false);
    };
    const handleEditButton = () => {// showing edit button
        setShowEdit(true);
    };

    const handleEditEvent = (id) => {
        const foundMeeting = allMeetingsData.filter(meeting => meeting.id === id);
        const setMeeting = {
            id: foundMeeting[0].id,
            title: foundMeeting[0].title,
            date: foundMeeting[0].date,
            startTime: foundMeeting[0].startTime,
            endTime: foundMeeting[0].endTime,
            level: foundMeeting[0].level,
            participants: foundMeeting[0].participants,
            description: foundMeeting[0].description
        };
        setMeetingFormData(setMeeting);
        handleEditButton();
        setEditId(id);
        setShowAlert(false);
        methods.clearErrors();
    };

    const handleUpdateMeeting = () => {
        updateMeetingAPICall();
        handleCreateButton();
        clearFields();
        postAPIOperation("UPDATED", "warning");
        document.getElementById("meetingform").style.display="none";
    };

    const handleDeleteMeeting = (deleteId) => {
        if(confirm("Do you want to cancel the meeting?")) {
            deleteMeetingAPICall(deleteId);
            handleCreateButton();
            methods.clearErrors();
            clearFields();
            postAPIOperation("CANCELLED", "danger");
        } else {
            methods.clearErrors();
            clearFields();
            postAPIOperation("Retained", "info");
        }
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
        document.getElementById("meetingform").style.display="none";
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

    const deleteMeetingAPICall = async (deleteId) => {
        try {
            const response = await axios.delete(`${apiEndpoint}/${deleteId}`);
            if(response.status === 204) {
                console.log("Meeting deleted successfully...");
                setReload(!reload);
            }
        } catch(error) {
            console.log("Error on deleting meeting...", error);
        }
    };

    return (
    <div className='container-fluid bg-light'>
        {showAlert && <AlertMessage icon={<FaCheckCircle />} 
            message={<>Meeting is successfully <b>{alertName}</b></>} 
            color={alertColor} />
        }

        <div className='row'>
            <div className='bg-white'>
                <FormProvider {...methods}>
                    <div id="meetingform" className="card mb-1" style={{display: "none"}}>
                        <div className="card-body">
                            <h5 className="card-title bg-primary ps-1 py-1 rounded text-white"><FaCalendarAlt /> Schedule a New Meeting</h5>
                            {meetingFormData && meetingFormData.id != null && <MeetingForm
                                meetingFormData={meetingFormData} setMeetingFormData={setMeetingFormData} 
                                showAlert={showAlert} setShowAlert={setShowAlert}
                                handleUpdateMeeting={handleUpdateMeeting}
                                clearFields={clearFields}
                                showEdit={showEdit} />}
                            
                        </div>
                    </div>
                </FormProvider>
                <div className="card my-2" style={{overflowY: "scroll", height: "550px"}}>
                    <div className="card-body">
                        <h5 className="card-title">List of Created Meetings</h5>
                        <MeetingsList 
                            allMeetingsData={allMeetingsData} 
                            handleDeleteMeeting={handleDeleteMeeting}
                            handleEditEvent={handleEditEvent} />
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
};

export default ManageMeetings;