import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './Navbar';
import Footer from './Footer';
import About from "../navbar/About";
import Contact from '../navbar/Contact';
import Services from '../navbar/Services';
import Home from '../navbar/Home';
import Dashboard from '../content/Dashboard';

const MeetingCalendarApp = () => {
    return (
    <Router>
        <Navbar />
        <Footer />
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/home/*" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/services" element={<Services />} />
            <Route path="/dashboard/*" element={<Dashboard />}/>
            <Route path="*" element={<PageNotFound />} />
        </Routes>
    </Router>
    );
};

const PageNotFound = () => {
    return(
        <div className='container-fluid'>
            <h2>Page Not Found Component</h2>
        </div>
    );
};

export default MeetingCalendarApp;