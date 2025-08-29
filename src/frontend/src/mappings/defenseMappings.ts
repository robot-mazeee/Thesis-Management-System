export const getColor = (status: string) => {
    switch(status) {
        case "IN_REVISION":
            return 'purple';
        case "DEFENSE_SCHEDULED":
            return 'yellow';
        case "SUBMITTED_TO_FENIX":
            return 'green';
        default:
            return 'red';
    }
}

export const translateDefenseStatusToIndex = (status: string) => {
    switch (status) {
        case "DEFENSE_SCHEDULED":
            return 0
        case "IN_REVISION":
            return 1;
        case "SUBMITTED_TO_FENIX":
            return 2;
        default:
            return -1;
    }
};