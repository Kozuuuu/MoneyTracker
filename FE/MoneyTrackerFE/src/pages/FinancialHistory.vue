<template>
  <q-page padding>
    <q-table
      v-if="!loading && filteredRecords.length > 0"
      :rows="filteredRecords"
      :columns="columns"
      row-key="payroll"
      :pagination="{
        rowsPerPage: 15,
        descending: true,
      }"
      :filter="filter"
      :dense="fullScreen"
      :loading="loading"
      class="q-py-sm table"
      no-data-label="Please Select History"
    >
      <!-- <div class="table-top-props">
        <h4 class="text-h6">Financial History</h4>
        <q-input
          filled
          v-model="search"
          label="Search"
          class="q-mr-sm"
          style="width: 250px"
        /> -->
      <!-- </div> -->
      <template v-slot:loading>
        <q-inner-loading showing color="primary" :label="`Gathering Data...`"
      /></template>

      <template #top-left>
        <div class="table-top-props">
          <div class="text-h5"><strong>Financial Records</strong></div>
        </div>
      </template>

      <template #top-right>
        <q-input
          v-model="filter"
          filled
          dense
          label="Search"
          debounce="300"
          color="primary"
          clearable
          style="width: 200px"
        >
          <template #append>
            <q-icon name="search" />
          </template>
        </q-input>
      </template>

      <template v-slot:body-cell-cash="props">
        <q-td :props="props">
          {{
            props.row.cash.toLocaleString("en-US", {
              style: "currency",
              currency: "PHP",
            })
          }}
        </q-td>
      </template>

      <template v-slot:body-cell-savings="props">
        <q-td :props="props">
          {{
            props.row.savings.toLocaleString("en-US", {
              style: "currency",
              currency: "PHP",
            })
          }}
        </q-td>
      </template>

      <template v-slot:body-cell-expenses="props">
        <q-td :props="props">
          {{
            props.row.expenses.toLocaleString("en-US", {
              style: "currency",
              currency: "PHP",
            })
          }}
        </q-td>
      </template>

      <template v-slot:body-cell-extra="props">
        <q-td :props="props">
          {{
            props.row.extra.toLocaleString("en-US", {
              style: "currency",
              currency: "PHP",
            })
          }}
        </q-td>
      </template>
    </q-table>

    <q-alert
      v-if="!loading && filteredRecords.length === 0"
      type="info"
      class="q-mt-md alert"
    >
      <strong>No records found.</strong>
    </q-alert>
  </q-page>
</template>

<script setup>
import axios from "axios";
import { computed, onMounted, ref } from "vue";

const fullScreen = ref(false);
const filter = ref("");
const mode = ref("");

const records = ref([]);
const loading = ref(true);
const search = ref("");
const columns = [
  {
    name: "period",
    label: "Period",
    field: "period",
    align: "left",
    sortable: true,
  },
  { name: "date", label: "Date", field: "date", align: "left", sortable: true },
  {
    name: "cash",
    label: "Cash",
    field: "cash",
    align: "right",
    sortable: true,
  },
  {
    name: "savings",
    label: "Savings",
    field: "savings",
    align: "right",
    sortable: true,
  },
  {
    name: "expenses",
    label: "Expenses",
    field: "expenses",
    align: "right",
    sortable: true,
  },
  {
    name: "extra",
    label: "Extra",
    field: "extra",
    align: "right",
    sortable: true,
  },
];

// Computed property for filtered records
const filteredRecords = computed(() => {
  return records.value.filter((record) =>
    record.period.toLowerCase().includes(search.value.toLowerCase())
  );
});

onMounted(() => {
  axios
    .get("http://localhost:7700/records")
    .then((response) => {
      records.value = response.data;
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    })
    .finally(() => {
      loading.value = false;
    });
});
</script>

<style scoped>
.table-top-props {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.table {
  border: 1px solid #e0e0e0; /* Table border */
  border-radius: 4px;
}

.table .q-tr:hover {
  background-color: #f0f0f0; /* Row hover effect */
}

.alert {
  background-color: #e3f2fd; /* Light blue for alert */
  border: 1px solid #bbdefb; /* Light blue border */
}
</style>
