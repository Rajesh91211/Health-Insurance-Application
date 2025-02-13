document.addEventListener('DOMContentLoaded', function() {
    // FAQ Accordion
    const faqQuestions = document.querySelectorAll('.faq-question');
    faqQuestions.forEach(question => {
        question.addEventListener('click', () => {
            const answer = question.nextElementSibling;
            const isOpen = answer.style.display === 'block';
            
            // Close all answers
            document.querySelectorAll('.faq-answer').forEach(a => {
                a.style.display = 'none';
            });
            
            // Open clicked answer if it was closed
            if (!isOpen) {
                answer.style.display = 'block';
            }
        });
    });

    // Modal Handling
    const modal = document.getElementById('modal');
    const closeButton = document.querySelector('.close-button');
    const learnMoreButtons = document.querySelectorAll('.learn-more');

    const planDetails = {
        individual: {
            title: 'Individual Health Insurance Plan',
            content: `
                <h3>Plan Details</h3>
                <ul>
                    <li>Coverage amount: Up to $500,000</li>
                    <li>Annual premium starting from $1,200</li>
                    <li>Zero waiting period for accidents</li>
                    <li>30-day waiting period for illnesses</li>
                    <li>Pre-existing conditions covered after 2 years</li>
                </ul>
                <h3>Additional Benefits</h3>
                <ul>
                    <li>Free annual health checkup</li>
                    <li>Cashless treatment at 5000+ hospitals</li>
                    <li>Coverage for day-care procedures</li>
                    <li>Alternative treatment coverage (Ayurveda, Homeopathy)</li>
                    <li>No claim bonus - 5% increase in sum insured</li>
                </ul>
            `
        },
        family: {
            title: 'Family Health Insurance Plan',
            content: `
                <h3>Plan Details</h3>
                <ul>
                    <li>Coverage amount: Up to $1,000,000</li>
                    <li>Annual premium starting from $2,400</li>
                    <li>Covers self, spouse, and up to 3 children</li>
                    <li>Maternity coverage after 2 years</li>
                    <li>New born baby coverage from day 1</li>
                </ul>
                <h3>Additional Benefits</h3>
                <ul>
                    <li>Free annual health checkup for all members</li>
                    <li>Cashless treatment at 5000+ hospitals</li>
                    <li>Coverage for day-care procedures</li>
                    <li>Alternative treatment coverage</li>
                    <li>No claim bonus - 10% increase in sum insured</li>
                </ul>
            `
        }
    };

    learnMoreButtons.forEach(button => {
        button.addEventListener('click', () => {
            const planType = button.getAttribute('data-plan');
            const planInfo = planDetails[planType];
            
            document.getElementById('modal-content').innerHTML = `
                <h2>${planInfo.title}</h2>
                ${planInfo.content}
            `;
            
            modal.style.display = 'block';
        });
    });

    closeButton.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Contact Form Handling
    const queryForm = document.getElementById('queryForm');
    queryForm.addEventListener('submit', (e) => {
        e.preventDefault();
        alert('Thank you for your query. Our team will contact you shortly!');
        queryForm.reset();
    });

    // Smooth Scrolling for Navigation
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
});