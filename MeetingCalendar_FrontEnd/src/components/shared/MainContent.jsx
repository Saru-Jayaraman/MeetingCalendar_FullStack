import React, { useEffect, useState } from 'react';
import { FormProvider, useForm } from 'react-hook-form';
import AlertMessage from '../content/AlertMessage';
import Dashboard from '../content/Dashboard';
import MeetingForm from '../content/MeetingForm';
import MeetingsList from '../content/MeetingsList';
import { FaCalendarAlt } from "react-icons/fa";
import { FaCheckCircle } from 'react-icons/fa';
import axios from 'axios';

const MainContent = () => {
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

    const handleCreateMeeting = () => {
        createMeetingAPICall();
        clearFields();
        postAPIOperation("CREATED", "success");
    };

    const handleUpdateMeeting = () => {
        updateMeetingAPICall();
        handleCreateButton();
        clearFields();
        postAPIOperation("EDITED", "warning");
    };

    const handleDeleteMeeting = (deleteId) => {
        if(confirm("Confirm to delete?")) {
            deleteMeetingAPICall(deleteId);
            handleCreateButton();
            methods.clearErrors();
            clearFields();
            postAPIOperation("DELETED", "danger");
        } else {
            methods.clearErrors();
            clearFields();
            postAPIOperation("CANCELLED", "info");
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

    useEffect(() => {
        fetchAllMeetingsAPICall();
    }, [reload]);

    return (
    <div className='container-fluid bg-light'>
        <div className='row'>
            <div className='col'>
                {showAlert && <AlertMessage icon={<FaCheckCircle />} 
                message=
                {
                <>
                    {alertName !== "CANCELLED" ? 
                        (<>Meeting is successfully <b>{alertName}</b></>) : (<>Deletion opertion is <b>{alertName}</b></>)}
                </>
                } 
                color={alertColor} />}
            </div>
        </div>

        <div className='row'>
            <div className='col-md-3'>
                <div className='my-2 ms-4'>
                    <Dashboard />
                </div>
            </div>
            <div className='col-md-9'>
                <div className='my-2 me-4 px-2 py-2 bg-white'>
                    <FormProvider {...methods}>
                        <div className="card mb-1">
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
                        <div className="card mb-1" style={{overflowY: "scroll", height: "150px"}}>
                            <div className="card-body">
                                <h5 className="card-title">List of Created Meetings</h5>
                                <MeetingsList 
                                    allMeetingsData={allMeetingsData} 
                                    handleDeleteMeeting={handleDeleteMeeting}
                                    handleEditEvent={handleEditEvent} />
                            </div>
                        </div>
                    </FormProvider>
                </div>
            </div>
        </div>
    </div>
    );
};

export default MainContent;