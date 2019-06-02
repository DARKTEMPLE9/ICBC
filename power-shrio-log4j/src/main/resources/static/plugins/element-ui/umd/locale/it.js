(function (global, factory) {
    if (typeof define === "function" && define.amd) {
        define('element/locale/it', ['module', 'exports'], factory);
    } else if (typeof exports !== "undefined") {
        factory(module, exports);
    } else {
        var mod = {
            exports: {}
        };
        factory(mod, mod.exports);
        global.ELEMENT.lang = global.ELEMENT.lang || {};
        global.ELEMENT.lang.it = mod.exports;
    }
})(this, function (module, exports) {
    'use strict';

    exports.__esModule = true;
    exports.default = {
        el: {
            colorpicker: {
                confirm: 'OK',
                clear: 'Pulisci'
            },
            datepicker: {
                now: 'Ora',
                today: 'Oggi',
                cancel: 'Cancella',
                clear: 'Pulisci',
                confirm: 'OK',
                selectDate: 'Seleziona data',
                selectTime: 'Seleziona ora',
                startDate: 'Data inizio',
                startTime: 'Ora inizio',
                endDate: 'Data fine',
                endTime: 'Ora fine',
                year: 'Anno',
                month1: 'Gennaio',
                month2: 'Febbraio',
                month3: 'Marzo',
                month4: 'Aprile',
                month5: 'Maggio',
                month6: 'Giugno',
                month7: 'Luglio',
                month8: 'Agosto',
                month9: 'Settembre',
                month10: 'Ottobre',
                month11: 'Novembre',
                month12: 'Dicembre',
                // week: 'settimana',
                weeks: {
                    sun: 'Dom',
                    mon: 'Lun',
                    tue: 'Mar',
                    wed: 'Mer',
                    thu: 'Gio',
                    fri: 'Ven',
                    sat: 'Sab'
                },
                months: {
                    jan: 'Gen',
                    feb: 'Feb',
                    mar: 'Mar',
                    apr: 'Apr',
                    may: 'Mag',
                    jun: 'Giu',
                    jul: 'Lug',
                    aug: 'Ago',
                    sep: 'Set',
                    oct: 'Ott',
                    nov: 'Nov',
                    dec: 'Dic'
                }
            },
            select: {
                loading: 'Caricamento',
                noMatch: 'Nessuna corrispondenza',
                noData: 'Nessun dato',
                placeholder: 'Seleziona'
            },
            cascader: {
                noMatch: 'Nessuna corrispondenza',
                loading: 'Caricamento',
                placeholder: 'Seleziona'
            },
            pagination: {
                goto: 'Vai a',
                pagesize: '/page',
                total: 'Totale {total}',
                pageClassifier: ''
            },
            messagebox: {
                confirm: 'OK',
                cancel: 'Cancella',
                error: 'Input non valido'
            },
            upload: {
                deleteTip: 'press delete to remove', // to be translated
                delete: 'Cancella',
                preview: 'Anteprima',
                continue: 'Continua'
            },
            table: {
                emptyText: 'Nessun dato',
                confirmFilter: 'Conferma',
                resetFilter: 'Reset',
                clearFilter: 'Tutti',
                sumText: 'Sum' // to be translated
            },
            tree: {
                emptyText: 'Nessun dato'
            },
            transfer: {
                noMatch: 'Nessuna corrispondenza',
                noData: 'Nessun dato',
                titles: ['List 1', 'List 2'], // to be translated
                filterPlaceholder: 'Enter keyword', // to be translated
                noCheckedFormat: '{total} items', // to be translated
                hasCheckedFormat: '{checked}/{total} checked' // to be translated
            }
        }
    };
    module.exports = exports['default'];
});