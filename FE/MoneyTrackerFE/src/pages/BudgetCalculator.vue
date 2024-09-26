<template>
  <div class="container">
    <div class="card">
      <h3>Budget Calculator</h3>

      <form @submit.prevent="calculate" id="transaction-form">
        <div class="form-group">
          <label for="amount">Amount</label>
          <input
            type="number"
            v-model="amount"
            min="7000"
            max="100000"
            step="500"
            required
          />
        </div>

        <div class="form-group">
          <label for="savings">Savings</label>
          <input
            type="number"
            v-model="savings"
            name="savings"
            min="1000"
            max="100000"
            step="500"
            required
          />
        </div>

        <div class="form-group">
          <label for="expensesPerDay">Daily Expenses</label>
          <input
            type="number"
            v-model="expensesPerDay"
            name="expenses"
            min="10"
            max="1000"
            step="10"
            required
          />
        </div>

        <div class="form-group">
          <label for="monthlyExpenses">Monthly Expenses</label>
          <input
            type="number"
            v-model="monthlyExpenses"
            name="expenses"
            min="1000"
            max="30000"
            step="500"
            required
          />
        </div>
        <button type="submit" class="btn">Calculate</button>
      </form>
    </div>
  </div>

  <div v-if="hasResult" class="container">
    <div class="card">
      <h3>Result</h3>
      <table>
        <thead>
          <tr>
            <th>Next Payroll Date</th>
            <th>Days till next Payroll</th>
            <th>Total Expenses</th>
            <th>Extra</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ result.nextPayrollDate }}</td>
            <td>{{ result.daysUntilNextPayroll }}</td>
            <td>₱ {{ result.totalExpenses }}</td>
            <td>₱ {{ result.extra }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      amount: 7000,
      savings: 5000,
      expensesPerDay: 100,
      monthlyExpenses: 5000,
      result: {},
      hasResult: false,
    };
  },
  methods: {
    async calculate() {
      try {
        const data = {
          cash: this.amount,
          savings: this.savings,
          expensesPerDay: this.expensesPerDay,
          monthlyExpenses: this.monthlyExpenses,
        };
        const response = await axios.post(
          "http://localhost:7700/calculator",
          data
        );
        this.result = response.data;
        this.hasResult = true;
      } catch (error) {
        console.error("error fetching result:", error);
      }
    },
  },
};
</script>

<style scoped>
.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  margin-bottom: 1rem;
}
.card h3 {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 28px;
  font-weight: bold;
}
.card h5 {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 130%;
  font-weight: bold;
}
</style>
