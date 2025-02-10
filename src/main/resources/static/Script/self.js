// self.js (Place in src/main/resources/static/Script/)
function filterPlans() {
    const age = document.getElementById('age').value;
    const planContainer = document.querySelector('.plan-container');
    planContainer.innerHTML = '';

    if (age >= 18) {
        const plans = getAvailablePlans(age);
        plans.forEach(plan => {
            const planCard = createPlanCard(plan);
            planContainer.appendChild(planCard);
        });
    } else {
        planContainer.innerHTML = "<p>You must be 18 or older to view plans.</p>";
    }
}

function getAvailablePlans(age) {
    const allPlans = [
        { id: 'gold', name: 'Gold Plan', coverage: 'Up to ₹10 Lakhs', premium: 20000, image: 'Goldicon.png', link: '/gold-plan' },
        { id: 'silver', name: 'Silver Plan', coverage: 'Up to ₹5 Lakhs', premium: 12000, image: 'SilveriCon.png', link: '/silver-plan' },
        { id: 'diamond', name: 'Diamond Plan', coverage: 'Up to ₹20 Lakhs', premium: 35000, image: 'Diamondicon.png', link: '/diamond-plan' }
    ];

    if (age < 30) {
      return allPlans.filter(plan => plan.id !== 'diamond');
    } else {
      return allPlans;
    }
}

function createPlanCard(plan) {
    const planCard = document.createElement('div');
    planCard.classList.add('plan-card');
    planCard.id = plan.id;

    planCard.innerHTML = `
        <img src="/Images/${plan.image}">
        <h2>${plan.name}</h2>
        <div class="coverage-details">
            <h3>Coverage</h3>
            <ul>
                <li>${plan.coverage}</li>
            </ul>
        </div>
        <div class="premium-details">
            <h3>Premium (Annual)</h3>
            <p>₹${plan.premium}</p>
        </div>
        <a href="/api/silver-plan"><button>Check Plan Details</button></a>
    `;

    return planCard;
}