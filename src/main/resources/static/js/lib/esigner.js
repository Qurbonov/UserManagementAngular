/**
 * Mehribon va rahmli Alloh nomi bilan
 * Intsoft Servis loyihalarining qismi.
 * @muallif Ermat E. Qiyomov. Boshlangan sana: 28.05.2014.
 */

var E_SIGNER_DOM_ELEMENT_ID = 'esigner';
var E_SIGNER_KEY_SOURCE = {
    AUTO: '',
    CACHE: 'cache://',
    FILE: 'file://',
    EKALIT: 'ekalit://'
};
var E_SIGNER_STATUS_CODES = {
    EE_SUCCESS: 0,
    EE_INPUT_DATA_NOT_SPECIFIED: 1,
    EE_FILE_NOT_EXISTS: 2,
    EE_NO_REMOVABLES_AVAILABLE: 3,
    EE_FILE_NOT_SPECIFIED: 4,
    EE_NO_ACCESS_TO_FILE: 5,
    EE_ACTION_CANCELED: 6,
    EE_UNSUPPORTED_ALGORITHM: 7,
    EE_INVALID_PRIVATE_KEY: 8,
    EE_INVALID_CERTIFICATE: 9,
    EE_INVALID_DIGEST: 10,
    EE_SIGNING_FAILED: 11,
    EE_EKALIT_DLL_NOT_FOUND: 12,
    EE_EKALIT_DLL_INVALID: 13,
    EE_EKALIT_ERROR_DEVICE_NOT_FOUND: 14,
    EE_EKALIT_ERROR_COULD_NOT_OPEN: 15,
    EE_EKALIT_ERROR_COULD_NOT_INIT: 16,
    EE_EKALIT_INCORRECT_PASSWORD: 17,
    EE_UNKNOWN: 18
};

var getESigner = function () {
    var eSigner = document.getElementById(E_SIGNER_DOM_ELEMENT_ID);
    if (eSigner === null) {
        eSigner = document.createElement('object');
        eSigner.id = E_SIGNER_DOM_ELEMENT_ID;
        eSigner.type = 'application/x-esigner';
        eSigner.style.height = 0;
        eSigner.style.width = 0;
        document.body.appendChild(eSigner);
    }
    return eSigner;
};

var eSignerIsValid = function () {
    var eSigner = getESigner();
    if (eSigner === null) {
        alert('no DOM object with id esigner');
        return false;
    }
    return eSigner.valid;
};

var eSignerFailed = function (errorInMethod) {
    var eSigner = getESigner();
    var res = eSigner.errCode;
    if (res !== E_SIGNER_STATUS_CODES.EE_SUCCESS && res !== E_SIGNER_STATUS_CODES.EE_ACTION_CANCELED) {
        alert('Ошибка в функции ' + errorInMethod + '() плагина. Код ошибки: 0x' + res.toString(16));
        return true;
    }
    return false;
};

var eSignerDigest = function (text) {
    if (!eSignerIsValid()) {
        return null;
    }
    var eSigner = getESigner();
    return eSigner.calcDigest(text);
};

var eSignerSign = function (keySource, sessionId, digest) {
    if (!eSignerIsValid()) {
        return null;
    }
    var eSigner = getESigner();
    var res = eSigner.signDigest(keySource, sessionId, digest);
    if (eSignerFailed('signDigest')) {
        return null;
    }
    return res;
};
