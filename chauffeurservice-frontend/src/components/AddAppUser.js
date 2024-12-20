import React, { useState } from 'react';

const UserCreate = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: '',
        active: true,
        role: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Kullanıcıyı backend'e kaydetmek için POST isteği gönderme
        try {
            const response = await fetch('http://localhost:8080/api/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                // Başarıyla kullanıcı oluşturulduğunda formu sıfırlıyoruz
                alert('User created successfully!');
                setFormData({
                    firstName: '',
                    lastName: '',
                    username: '',
                    email: '',
                    password: '',
                    active: true,
                    role: '',
                });
            } else {
                alert('Failed to create user!');
            }
        } catch (error) {
            console.error('Error creating user:', error);
            alert('Error creating user!');
        }
    };

    return (
        <div>
            <h2>Create User</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name</label>
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Last Name</label>
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Username</label>
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Email</label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Password</label>
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div>
                    <label>Active</label>
                    <input
                        type="checkbox"
                        name="active"
                        checked={formData.active}
                        onChange={(e) =>
                            setFormData({ ...formData, active: e.target.checked })
                        }
                    />
                </div>

                <div>
                    <label>Role</label>
                    <input
                        type="text"
                        name="role"
                        value={formData.role}
                        onChange={handleChange}
                        required
                    />
                </div>

                <button type="submit">Create User</button>
            </form>
        </div>
    );
};

export default UserCreate;
