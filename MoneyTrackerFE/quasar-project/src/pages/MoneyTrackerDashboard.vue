<template>
  <q-page>
    <header class="q-pa-md">
      <h1>Money Tracker</h1>
      <q-btn @click="toggleNightMode" icon="sun" id="nightModeToggle">
        <template v-slot:default>
          <i :class="nightMode ? 'fas fa-moon' : 'fas fa-sun'"></i>
        </template>
      </q-btn>
    </header>

    <div class="q-gutter-md q-pa-md">
      <q-card>
        <q-card-section>
          <h2>Dashboard</h2>
          <div class="summary q-gutter-md">
            <div>
              <h3>Total Income</h3>
              <p>$2,500</p>
            </div>
            <div>
              <h3>Total Expenses</h3>
              <p>$1,200</p>
            </div>
            <div>
              <h3>Total Savings</h3>
              <p>$1,300</p>
            </div>
          </div>
          <div class="chart-container">
            <!-- Chart or graph would go here -->
          </div>
        </q-card-section>
      </q-card>

      <q-card>
        <q-card-section>
          <h2>Add Financial Record</h2>
          <form @submit.prevent="addTransaction">
            <div class="q-gutter-md">
              <q-select filled v-model="transaction.type" label="Type">
                <q-option value="income" label="Income" />
                <q-option value="expense" label="Expense" />
              </q-select>

              <q-input
                filled
                v-model.number="transaction.amount"
                type="number"
                label="Amount"
                required
              />
              <q-input
                filled
                v-model.number="transaction.savings"
                type="number"
                label="Savings"
                required
              />
              <q-input
                filled
                v-model="transaction.savingsDescription"
                label="Savings Description"
              />
              <q-input
                filled
                v-model.number="transaction.expenses"
                type="number"
                label="Expenses"
                required
              />
              <q-input
                filled
                v-model="transaction.expensesDescription"
                label="Expenses Description"
              />
              <q-input filled v-model="transaction.category" label="Category" />

              <q-btn type="submit" label="Add Transaction" />
            </div>
          </form>
        </q-card-section>
      </q-card>

      <q-card>
        <q-card-section>
          <h2>View Financial Records</h2>
          <q-table :rows="transactions" :columns="columns" row-key="date" />
        </q-card-section>
      </q-card>
    </div>
  </q-page>
</template>

<script>
export default {
  data() {
    return {
      nightMode: false,
      transaction: {
        type: "income",
        amount: null,
        savings: null,
        savingsDescription: "",
        expenses: null,
        expensesDescription: "",
        category: "",
      },
      transactions: [
        {
          date: "2024-09-19",
          type: "Expense",
          amount: "$50",
          category: "Groceries",
        },
        // Add more transactions here
      ],
      columns: [
        { name: "date", label: "Date", align: "left", field: "date" },
        { name: "type", label: "Type", align: "left", field: "type" },
        { name: "amount", label: "Amount", align: "left", field: "amount" },
        {
          name: "category",
          label: "Category",
          align: "left",
          field: "category",
        },
      ],
    };
  },
  methods: {
    toggleNightMode() {
      this.nightMode = !this.nightMode;
      // Add logic for night mode styling here
    },
    addTransaction() {
      // Logic to handle adding a transaction
      console.log("Transaction added:", this.transaction);
      // Reset the form after submission
      this.transaction = {
        type: "income",
        amount: null,
        savings: null,
        savingsDescription: "",
        expenses: null,
        expensesDescription: "",
        category: "",
      };
    },
  },
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background: #f5f5f5;
}
header {
  background: #007bff;
  color: white;
  padding: 0.5rem 1rem;
  text-align: center;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 3t0px;
}
.container {
  max-width: 700px;
  max-height: fit-content;
  margin: 0 auto;
  padding: 1rem;
  align-self: center;
}
.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  margin-bottom: 1rem;
}
.card h2 {
  margin-top: 0;
}
.form-group {
  margin-bottom: 1rem;
}
.form-group label {
  display: block;
  margin-bottom: 0.5rem;
}
.form-group input,
.form-group select {
  width: 100%;
  padding: 0.5rem;
  border-radius: 4px;
  border: 1px solid #ddd;
}
.btn {
  background-color: #007bff;
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
}
.btn:hover {
  background-color: #0056b3;
}
.summary {
  display: flex;
  justify-content: space-between;
}
.summary div {
  flex: 1;
  margin-right: 1rem;
}
.summary div:last-child {
  margin-right: 0;
}
.chart-container {
  margin-top: 1rem;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}
table,
th,
td {
  border: 1px solid #ddd;
}
th,
td {
  padding: 0.5rem;
  text-align: left;
}
th {
  background-color: #f4f4f4;
}

/* Night Mode Styles */
.night-mode {
  background-color: #1c1c1c;
  color: #f1f1f1;
}

.night-mode .card {
  background-color: #333;
  color: #f1f1f1;
}

.night-mode header {
  background-color: #111;
  color: #f1f1f1;
}

.night-mode .btn:hover {
  background-color: #666;
}
.night-mode .btn {
  background-color: #555;
}

/* .night-mode input {
  background: #555;
  border-color: #666;
} */

.night-mode .night-mode-toggle {
  background-color: #555;
  color: #ddd;
  border: 1px solid #666;
}
.night-mode .night-mode-toggle:hover {
  background-color: #666;
}

.night-mode label {
  color: #ddd;
}

header button {
  margin-left: auto;
  padding: 0.5rem 1rem;
  background-color: white;
  color: #111;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1.5rem; /* Adjust icon size */
}

header button:hover {
  background-color: whitesmoke;
}

.night-mode th {
  background-color: #555;
}
</style>
